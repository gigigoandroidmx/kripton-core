package com.gigigo.kbranding.adapter;

import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * Created by Omar on 8/1/17.
 */

public class TabIndicatorColorAdapter implements ColorAdapter<TabLayout> {

    @Override
    public void applyColor(TabLayout view, @ColorInt int color) {
        view.setSelectedTabIndicatorColor(color);
    }

    @Override
    public int getColor(TabLayout view) {
        return 0;
    }
}
