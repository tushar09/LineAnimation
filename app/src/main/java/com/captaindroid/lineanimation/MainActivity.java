package com.captaindroid.lineanimation;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LineAnimation lineAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineAnimation = findViewById(R.id.la_view);
        Path p = new Path();
        p.moveTo(lineAnimation.getWidth() / 2, 0);
        p.cubicTo(0, lineAnimation.getHeight() / 2, lineAnimation.getWidth(), lineAnimation.getHeight() / 2, lineAnimation.getWidth() / 2, lineAnimation.getHeight());
        lineAnimation.setPath(p);
        lineAnimation.startAnimateArrow();
    }
}
