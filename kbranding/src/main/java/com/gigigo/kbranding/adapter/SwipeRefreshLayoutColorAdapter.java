package com.gigigo.kbranding.adapter;

import android.support.annotation.ColorInt;
import android.support.v4.widget.SwipeRefreshLayout;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Juan Godínez Vera - 7/31/2017.
 */
public class SwipeRefreshLayoutColorAdapter
        implements ColorAdapter<SwipeRefreshLayout> {

    @Override
    public void applyColor(SwipeRefreshLayout view, @ColorInt int color) {
        view.setColorSchemeColors(color);
    }

    @Override
    public int getColor(SwipeRefreshLayout view) {
        return 0;
    }
}
