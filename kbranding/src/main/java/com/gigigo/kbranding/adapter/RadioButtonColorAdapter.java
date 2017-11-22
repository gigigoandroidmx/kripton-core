package com.gigigo.kbranding.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.widget.RadioButton;

import com.ftinc.scoop.adapters.ColorAdapter;

/**
 * @author Omar Bacilio - 11/15/17.
 */

public class RadioButtonColorAdapter implements ColorAdapter<RadioButton> {

    @Override
    public void applyColor(RadioButton view, int color) {
        if (view.getBackground() instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) view.getBackground();
            DrawableContainer.DrawableContainerState drawableContainerState =
                    (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
            Drawable[] children = drawableContainerState.getChildren();

            if (children != null) {
                GradientDrawable state_checked_and_pressed = (GradientDrawable) children[0];
                GradientDrawable state_pressed = (GradientDrawable) children[1];
                GradientDrawable state_selected = (GradientDrawable) children[2];
                GradientDrawable state_normal = (GradientDrawable) children[3];

                int grayColor = Color.parseColor("#bdbdbd");
                state_checked_and_pressed.setColor(grayColor);
                state_pressed.setColor(grayColor);
                state_selected.setColor(color);
                state_normal.setColor(grayColor);
            }
        } else if (view.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) view.getBackground()).setColor(color);
        }
    }

    @Override
    public int getColor(RadioButton view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (view.getBackgroundTintList() != null) {
                return view.getBackgroundTintList().getDefaultColor();
            }
        }

        return 0;
    }
}
