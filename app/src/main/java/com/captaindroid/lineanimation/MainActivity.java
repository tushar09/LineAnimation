package com.captaindroid.lineanimation;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.captaindroid.lineanimation.utils.MyPath;
import com.captaindroid.lineanimation.utils.OnPathListener;

public class MainActivity extends AppCompatActivity implements OnPathListener {

    Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animator = findViewById(R.id.la_view);
        Path p = new MyPath();
        p.moveTo(animator.getWidth() / 2, 0);
        p.cubicTo(0, animator.getHeight() / 2, animator.getWidth(), animator.getHeight() / 2, animator.getWidth() / 2, animator.getHeight());
        animator.setPath(p);
        animator.startAnimateArrow();
    }


    float t = 2;
    float d = 1;
    @Override
    public Path setOnPathUpdateListener(int bitmapPositionX, int bitmapPositionY){
        Path p = new Path();
        p.moveTo(animator.getWidth() / t, 0);
        p.cubicTo(0, animator.getHeight() / t, animator.getWidth(), animator.getHeight() / t, animator.getWidth() / t, animator.getHeight());

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

    }
}
