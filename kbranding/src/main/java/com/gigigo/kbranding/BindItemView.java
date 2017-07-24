package com.gigigo.kbranding;

import android.view.View;

import com.ftinc.scoop.adapters.ColorAdapter;


/**
 * @author Juan Godínez Vera - 7/5/2017.
 */
public class BindItemView {

    private final int toppingId;
    private final View view;
    private final ColorAdapter colorAdapter;

    public BindItemView(int toppingId, View view, ColorAdapter colorAdapter) {
        this.toppingId = toppingId;
        this.view = view;
        this.colorAdapter = colorAdapter;
    }

    public int getToppingId() {
        return toppingId;
    }

    public View getView() {
        return view;
    }

    public ColorAdapter getColorAdapter() {
        return colorAdapter;
    }
}
