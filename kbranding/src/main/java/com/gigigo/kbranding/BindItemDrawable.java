package com.gigigo.kbranding;

import android.graphics.drawable.Drawable;

/**
 * @author Juan Godínez Vera - 7/7/2017.
 */
public class BindItemDrawable {

    private final int toppingId;
    private final Drawable drawable;

    public BindItemDrawable(int toppingId, Drawable drawable) {
        this.toppingId = toppingId;
        this.drawable = drawable;
    }

    public int getToppingId() {
        return toppingId;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
