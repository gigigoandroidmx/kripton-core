package com.gigigo.kbranding;

import android.widget.ImageView;

/**
 * @author Juan Godínez Vera - 7/7/2017.
 */
public class BindItemImage {
    private final int toppingId;
    private final ImageView imageView;

    public BindItemImage(int toppingId, ImageView view) {
        this.toppingId = toppingId;
        this.imageView = view;
    }

    public int getToppingId() {
        return toppingId;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
