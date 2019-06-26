package com.captaindroid.lineanimation;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.captaindroid.lineanimation.utils.MyPath;
import com.captaindroid.lineanimation.utils.OnPathListener;

public class MainActivity extends AppCompatActivity implements OnPathListener {

    LineAnimation lineAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineAnimation = findViewById(R.id.la_view);
        Path p = new MyPath();
        p.moveTo(lineAnimation.getWidth() / 2, 0);
        p.cubicTo(0, lineAnimation.getHeight() / 2, lineAnimation.getWidth(), lineAnimation.getHeight() / 2, lineAnimation.getWidth() / 2, lineAnimation.getHeight());
        lineAnimation.setPath(p);
        lineAnimation.startAnimateArrow();
    }

    float t = 2;
    float d = 1;
    @Override
    public Path setOnUpdatePath() {
        Path p = new Path();
        p.moveTo(lineAnimation.getWidth() / t, 0);
        p.cubicTo(0, lineAnimation.getHeight() / t, lineAnimation.getWidth(), lineAnimation.getHeight() / t, lineAnimation.getWidth() / t, lineAnimation.getHeight());

        if(t > 10){
            d = -0.009f;
        }else if(t < 2){
            d = 0.009f;
        }

        t += d;

        Log.e("t", t + "");
        return p;
    }
}
