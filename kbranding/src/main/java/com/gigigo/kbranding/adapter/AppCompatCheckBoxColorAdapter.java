package com.gigigo.kbranding.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CheckBox;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Juan Godínez Vera - 8/31/2017.
 */
public class AppCompatCheckBoxColorAdapter
        implements ColorAdapter<AppCompatCheckBox> {

    @Override
    public void applyColor(AppCompatCheckBox view, @ColorInt int color) {
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[]{

                        Color.GRAY,
                        color,
                }
        );

        view.setSupportButtonTintList(colorStateList);
    }

    @Override
    public int getColor(AppCompatCheckBox view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (view.getBackgroundTintList() != null) {
                return view.getBackgroundTintList().getDefaultColor();
            }
        }

        return 0;
    }
}
