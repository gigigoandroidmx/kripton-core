package com.gigigo.kbranding.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Juan Godínez Vera - 7/13/2017.
 */
public class TabLayoutColorAdapter
        implements ColorAdapter<TabLayout> {

    @Override
    public void applyColor(TabLayout view, @ColorInt int color) {
        for(int index = 0; index < view.getTabCount(); index++) {
            // Se elimina el padding del tab
            LinearLayout layout = ((LinearLayout) ((LinearLayout) view.getChildAt(0)).getChildAt(index));
            // Se coloca un margen izquierdo sobre el tab
            View tab = ((ViewGroup) view.getChildAt(0)).getChildAt(index);

            if(layout != null && tab != null) {

                if(tab.getBackground() instanceof StateListDrawable) {
                    StateListDrawable stateListDrawable = (StateListDrawable) tab.getBackground();
                    DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
                    Drawable[] children = drawableContainerState.getChildren();

                    if(children != null) {
                        GradientDrawable state_enabled = (GradientDrawable) children[0];
                        GradientDrawable state_selected = (GradientDrawable) children[1];
                        GradientDrawable state_pressed = (GradientDrawable) children[2];
                        GradientDrawable state_default = (GradientDrawable) children[3];

                        state_enabled.setColor(Color.parseColor("#bdbdbd"));
                        state_selected.setColor(color);
                        state_pressed.setColor(ColorUtils.setAlphaComponent(color, 125));
                        state_default.setColor(Color.parseColor("#00ffffff"));
                    }
                }
            }
        }


    }

    @Override
    public int getColor(TabLayout view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (view.getBackgroundTintList() != null) {
                return view.getBackgroundTintList().getDefaultColor();
            }
        }

        return 0;
    }
}
