package com.gigigo.kbranding.adapter;

import android.support.annotation.ColorInt;
import android.widget.Button;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * Created by Omar on 10/9/17.
 */

public class ButtonTextColorAdapter implements ColorAdapter<Button> {
    @Override
    public void applyColor(Button view, @ColorInt int color) {
        view.setTextColor(color);
    }

    @Override
    public int getColor(Button view) {
        return view.getCurrentTextColor();
    }
}
