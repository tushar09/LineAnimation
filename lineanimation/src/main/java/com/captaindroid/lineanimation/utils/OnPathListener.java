package com.captaindroid.lineanimation.utils;

import android.graphics.Path;

public interface OnPathListener {
    Path setOnPathUpdateListener(int bitmapPositionX, int bitmapPositionY);
    void setOnAnimationCompleteListener();
}
