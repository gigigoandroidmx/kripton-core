package com.gigigo.kbranding.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * Created by Omar on 7/18/17.
 */

public class EditTextColorAdapter implements ColorAdapter<EditText> {
    @Override
    public void applyColor(EditText view, @ColorInt int color) {
        Drawable background = view.getBackground();
        DrawableCompat.setTint(background, color);
        view.setBackground(background);
    }

    @Override
    public int getColor(EditText view) {
        return 0;
    }
}
