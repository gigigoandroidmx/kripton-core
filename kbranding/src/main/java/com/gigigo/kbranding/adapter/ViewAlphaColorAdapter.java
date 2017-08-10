package com.gigigo.kbranding.adapter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.v4.graphics.ColorUtils;
import android.view.View;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Juan Godínez Vera - 8/10/2017.
 */
public class ViewAlphaColorAdapter
        implements ColorAdapter<View> {

    private final int alpha;

    public ViewAlphaColorAdapter(@IntRange(from = 0x0, to = 0xFF) int alpha) {
        this.alpha = alpha;
    }

    @Override
    public void applyColor(View view, @ColorInt int color) {
        view.setBackgroundColor(ColorUtils.setAlphaComponent(color, alpha));
    }

    @Override
    public int getColor(View view) {
        Drawable bg = view.getBackground();
        if(bg instanceof ColorDrawable){
            return ((ColorDrawable) bg).getColor();
        }
        return 0;
    }
}
