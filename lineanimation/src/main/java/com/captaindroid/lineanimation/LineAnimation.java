package com.captaindroid.lineanimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.captaindroid.lineanimation.utils.Coordinates;
import com.captaindroid.lineanimation.utils.OnPathListener;

import java.util.ArrayList;

public class LineAnimation extends View{

    private int pathColor;
    private int dashPathSize;
    private int dashPathGap;
    private int pathStrokeWidth;
    private int drawableAnimationSpeed;
    private int drawable;
    private boolean enableDashPath;
    private boolean repeatable;

    public Path userPath;
    private Path line;

    private Matrix matrix;
    private Bitmap arrow;
    private Paint paint;

    private ArrayList<Coordinates> coordinates;
    private ArrayList<Coordinates> coordinatesTan;
    private int arrowX;
    private int arrowY;
    private int traveler = 0;

    private boolean animateArrow = false;

    private Context context;

    private float arrowXTan;
    private float arrowYTan;

    public LineAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LineAnimation, 0, 0);
        try {
            pathColor = ta.getColor(R.styleable.LineAnimation_pathColor, Color.BLACK);
            dashPathSize = (int) ta.getDimension(R.styleable.LineAnimation_dashPathSize, 30f);
            dashPathGap = (int) ta.getDimension(R.styleable.LineAnimation_dashPathGap, 30f);
            pathStrokeWidth = (int) ta.getDimension(R.styleable.LineAnimation_pathStrokeWidth, 4f);
            drawableAnimationSpeed = ta.getInteger(R.styleable.LineAnimation_drawableAminationSpeed, 9);
            drawable = ta.getResourceId(R.styleable.LineAnimation_drawable, R.drawable.ic_roket);
            enableDashPath = ta.getBoolean(R.styleable.LineAnimation_enableDashPath, true);
            repeatable = ta.getBoolean(R.styleable.LineAnimation_enableDashPath, false);
        } finally {
            ta.recycle();
        }

        line = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        if(enableDashPath){
            paint.setPathEffect(new DashPathEffect(new float[]{dashPathSize, dashPathGap}, 0));
        }

        arrow = getBitmap(drawable);

        this.context = context;

        matrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(pathColor);
        paint.setStrokeWidth(pathStrokeWidth);
        OnPathListener opl = (OnPathListener) context;
        line = opl.setOnPathListener(arrowX, arrowY);

        PathMeasure pm = new PathMeasure(line, false);
        float aCoordinates[] = {0f, 0f};
        float aTan[] = {0f, 0f};
        //if(coordinates == null){
            coordinates = new ArrayList<>();
            coordinatesTan = new ArrayList<>();
            for(int x = (int) pm.getLength(); x > 0; x--){
                pm.getPosTan(x, aCoordinates, aTan);
                Coordinates c = new Coordinates();
                c.setX((int) aCoordinates[0]);
                c.setY((int) aCoordinates[1]);
                coordinates.add(c);

                Coordinates c2 = new Coordinates();
                c2.setXf(aTan[0]);
                c2.setYf(aTan[1]);
                coordinatesTan.add(c2);
            }
        //}

        canvas.drawPath(line, paint);
        motionBitmap();

        matrix.reset();
        float degrees = (float) (Math.atan2(arrowXTan, arrowYTan) * -180.0 / Math.PI);
        matrix.postRotate(degrees, arrow.getWidth() / 2, arrow.getHeight() / 2);
        matrix.postTranslate(arrowX - (arrow.getWidth() / 2), arrowY - (arrow.getHeight() / 2));

        canvas.drawBitmap(arrow, matrix, null);
        if(animateArrow){
            invalidate();
        }
    }

    private void motionBitmap(){
        try{
            if(coordinates != null && coordinates.size() > 0){
                arrowX = coordinates.get(traveler).getX();
                arrowY = coordinates.get(traveler).getY();

                arrowXTan = coordinatesTan.get(traveler).getXf();
                arrowYTan = coordinatesTan.get(traveler).getYf();

                traveler += drawableAnimationSpeed;
                if(traveler > coordinates.size() - 1){
                    //arrow = null;
                    arrowX = coordinates.get(coordinates.size() - 1).getX();
                    arrowY = coordinates.get(coordinates.size() - 1).getY();
                    if(repeatable){
                        animateArrow = true;
                    }else {
                        animateArrow = false;
                    }
                    traveler = 0;
                }

                if(!animateArrow){
                    arrowX = coordinates.get(coordinates.size() - 1).getX();
                    arrowY = coordinates.get(coordinates.size() - 1).getY();
                }

            }
        }catch (Exception e){

        }

    }

    private Bitmap getBitmap(int drawableRes){
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void startAnimateArrow(){
        animateArrow = true;
        invalidate();
        requestLayout();
    }

    public void setPath(Path line){
        userPath = line;
    }

    public int getPathColor(){
        return pathColor;
    }

    public void setPathColor(int pathColor){
        this.pathColor = pathColor;
    }

    public int getDashPathSize(){
        return dashPathSize;
    }

    public void setDashPathSize(int dashPathSize){
        this.dashPathSize = dashPathSize;
    }

    public int getDashPathGap(){
        return dashPathGap;
    }

    public void setDashPathGap(int dashPathGap){
        this.dashPathGap = dashPathGap;
    }

    public int getPathStrokeWidth(){
        return pathStrokeWidth;
    }

    public void setPathStrokeWidth(int pathStrokeWidth){
        this.pathStrokeWidth = pathStrokeWidth;
    }

    public int getDrawableAnimationSpeed(){
        return drawableAnimationSpeed;
    }

    public void setDrawableAnimationSpeed(int drawableAnimationSpeed){
        this.drawableAnimationSpeed = drawableAnimationSpeed;
    }

    public int getDrawable(){
        return drawable;
    }

    public void setDrawable(int drawable){
        this.drawable = drawable;
    }

    public boolean isEnableDashPath(){
        return enableDashPath;
    }

    public void setEnableDashPath(boolean enableDashPath){
        this.enableDashPath = enableDashPath;
    }

    public boolean isRepeatable(){
        return repeatable;
    }

    public void setRepeatable(boolean repeatable){
        this.repeatable = repeatable;
    }

}
