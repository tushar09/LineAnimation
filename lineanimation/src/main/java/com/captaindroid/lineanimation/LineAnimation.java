package com.captaindroid.lineanimation;

import android.content.Context;
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

import java.util.ArrayList;

public class LineAnimation extends View{

    private Matrix matrix;
    private Bitmap arrow;
    private Path line;
    private Paint paint;

    ArrayList<Coordinates> coordinates;
    ArrayList<Coordinates> coordinatesTan;
    private int arrowX;
    private int arrowY;
    private int traveler = 0;

    public boolean animateArrow = false;

    private Context context;

    private float arrowXTan;
    private float arrowYTan;

    private int speed = 9;

    public LineAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        line = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 8}, 0));
        arrow = getBitmap(R.drawable.ic_arrow);

        this.context = context;

        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        line = new Path();
        paint.setColor(Color.parseColor(context.getString(R.color.dash_path)));
        paint.setStrokeWidth(4);
        line.moveTo(getWidth() / 2, 0);
        line.cubicTo(8, getHeight() / 2, getWidth(), getHeight() / 2, getWidth() / 2, getHeight());

        PathMeasure pm = new PathMeasure(line, false);
        float aCoordinates[] = {0f, 0f};
        float aTan[] = {0f, 0f};
        if(coordinates == null){
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
        }


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
        if(coordinates != null){
            arrowX = coordinates.get(traveler).getX();
            arrowY = coordinates.get(traveler).getY();

            arrowXTan = coordinatesTan.get(traveler).getXf();
            arrowYTan = coordinatesTan.get(traveler).getYf();

            traveler += speed;
            if(traveler > coordinates.size() - 1){
                //arrow = null;
                arrowX = coordinates.get(coordinates.size() - 1).getX();
                arrowY = 16;
                animateArrow = false;
                traveler = 0;
            }

            if(!animateArrow){
                arrowX = coordinates.get(coordinates.size() - 1).getX();
                arrowY = 16;
            }

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
}
