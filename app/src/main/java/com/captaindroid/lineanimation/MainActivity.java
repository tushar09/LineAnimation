package com.captaindroid.lineanimation;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.captaindroid.lineanimation.utils.MyPath;
import com.captaindroid.lineanimation.utils.OnPathListener;

public class MainActivity extends AppCompatActivity implements OnPathListener {

    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animator = findViewById(R.id.la_view);
        animator.startAnimateArrow();

        //getter and setter
        //animator.getPathColor();
        //animator.setPathColor(int pathColor);
        //animator.getDashPathSize();
        //animator.setDashPathSize(int dashPathSize);
        //animator.getDashPathGap();
        //animator.setDashPathGap(int dashPathGap);
        //animator.getPathStrokeWidth();
        //animator.setPathStrokeWidth(int pathStrokeWidth);
        //animator.getDrawableAnimationSpeed();
        //animator.setDrawableAnimationSpeed(int drawableAnimationSpeed);
        //animator.getDrawable();
        //animator.setDrawable(int drawable);
        //animator.isEnableDashPath();
        //animator.setEnableDashPath(boolean enableDashPath);
        //animator.isRepeatable();
        //animator.setRepeatable(boolean repeatable);


    }


    float t = 2;
    float d = 1;
    @Override
    public Path setOnPathUpdateListener(int bitmapPositionX, int bitmapPositionY){
        Path p = new Path();
        p.moveTo(animator.getWidth() / t, 0);
        p.cubicTo(0, animator.getHeight() / t, animator.getWidth(), animator.getHeight() / t, animator.getWidth() / t, animator.getHeight());
        //or
        //p.addCircle(...);
        //p.addArc(...);
        //p.quadTo(...);
        //just add an return your custom path

        if(t > 10){
            d = -0.009f;
        }else if(t < 2){
            d = 0.009f;
        }

        t += d;
        return p;
    }

    @Override
    public void setOnAnimationCompleteListener() {
        // completed the animation
    }
}
