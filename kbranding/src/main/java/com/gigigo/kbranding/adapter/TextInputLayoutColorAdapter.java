package com.gigigo.kbranding.adapter;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;
import android.widget.TextView;

import com.ftinc.scoop.adapters.ColorAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Juan Godínez Vera - 7/4/2017.
 */
public class TextInputLayoutColorAdapter implements ColorAdapter<TextInputLayout> {
    @Override
    public void applyColor(TextInputLayout view, @ColorInt int color) {
        Drawable background = view.getEditText().getBackground();
        DrawableCompat.setTint(background, color);
        view.getEditText().setBackground(background);
        setUpperHintColor(view, color);
        setCursorColor(view.getEditText(), color);
    }

    @Override
    public int getColor(TextInputLayout view) {
        return 0;
    }

    // https://stackoverflow.com/questions/35683379/programmatically-set-textinputlayout-hint-text-color-and-floating-label-color
    public void setUpperHintColor(TextInputLayout textInputLayout, int color) {
        try {
            Field field = textInputLayout.getClass().getDeclaredField("mFocusedTextColor");
            field.setAccessible(true);
            int[][] states = new int[][] {
                    new int[] {}
            };
            int[] colors = new int[] { color };
            ColorStateList colorStateList = new ColorStateList(states, colors);
            field.set(textInputLayout, colorStateList);

            Method method = textInputLayout.getClass().getDeclaredMethod("updateLabelState", boolean.class);
            method.setAccessible(true);
            method.invoke(textInputLayout, true);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // https://stackoverflow.com/questions/25996032/how-to-change-programatically-edittext-cursor-color-in-android
    public void setCursorColor(EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, drawables);
        } catch (Exception ignored) {
        }
    }
}
