package com.gigigo.kbranding.adapter;

import android.support.annotation.ColorInt;
import android.view.View;

/**
 * Created by Omar on 8/10/17.
 */

public class ButtonSelectionCustomColorAdapter extends DetectionFormColorAdapter {

    private int customColor;

    public ButtonSelectionCustomColorAdapter(int customColor) {
        this.customColor = customColor;
    }

    @Override
    public void applyColor(View view, @ColorInt int color) {
        super.applyColor(view, customColor);
    }
}
