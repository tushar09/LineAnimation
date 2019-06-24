package com.captaindroid.lineanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
    }
}
