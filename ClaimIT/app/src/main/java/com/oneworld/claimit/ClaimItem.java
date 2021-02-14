package com.oneworld.claimit;

import android.graphics.Bitmap;

public class ClaimItem {
    private final Bitmap bitmap;
    private final int points;

    public ClaimItem(Bitmap bitmap) {
        this(bitmap, 1);
    }
    public ClaimItem(Bitmap bitmap, int points) {
        this.bitmap = bitmap;
        this.points = points;
    }

    public Bitmap getIBitmap() {
        return bitmap;
    }

    public int getPoints() {
        return points;
    }
}
