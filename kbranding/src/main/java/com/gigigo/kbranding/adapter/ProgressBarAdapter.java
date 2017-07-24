package com.gigigo.kbranding.adapter;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ProgressBar;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Juan Godínez Vera - 7/7/2017.
 */
public class ProgressBarAdapter
        implements ColorAdapter<ProgressBar> {
    @Override
    public void applyColor(ProgressBar view, @ColorInt int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(view.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, color);
            view.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            view.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public int getColor(ProgressBar view) {
        return 0;
    }
}
