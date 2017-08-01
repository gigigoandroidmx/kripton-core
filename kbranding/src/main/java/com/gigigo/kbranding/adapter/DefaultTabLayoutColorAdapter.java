package com.gigigo.kbranding.adapter;

import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * Created by Omar on 8/1/17.
 */

public class DefaultTabLayoutColorAdapter implements ColorAdapter<TabLayout> {

    @Override
    public void applyColor(TabLayout view, @ColorInt int color) {
        view.setBackgroundColor(color);
        view.setTabGravity(TabLayout.GRAVITY_FILL);
        view.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public int getColor(TabLayout view) {
        return 0;
    }
}
