package com.gigigo.kbase.presentation.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Omar on 8/14/17.
 */

public class General {
    public static void hideKeyboard(Context context, View input) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }
}
