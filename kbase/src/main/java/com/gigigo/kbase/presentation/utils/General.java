package com.gigigo.kbase.presentation.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }
}
