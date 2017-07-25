package com.gigigo.kbranding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gigigo.kbranding.binding.BrandLabel;

/**
 * @author Juan Godínez Vera - 24/07/2017.
 */

public class BrandingManager {

    private static final String TAG = BrandingManager.class.getSimpleName();
    public static final String BRAND_LABEL_KEY = "brand_label_key";

    private static SharedSettings sharedSettings;
    private static BrandingSettings brandingSettings;


    public static BrandingSettings init(@NonNull Context context) {
        sharedSettings = new SharedSettings();
        sharedSettings.setContext(context);
        sharedSettings.setSharedPreferencesName("BrandLabelSharedPreferences");

        brandingSettings = new BrandingSettings();
        brandingSettings.setContext(context);

        return brandingSettings;
    }

    public static String getEndPoint() {
        return brandingSettings != null ? brandingSettings.getEndPoint() : "";
    }

    public static SharedSettings getSettings() {
        return sharedSettings;
    }

    public static BrandLabel getBrandLabel(BrandLabel defaultValue) {
        try {
            if(getSettings() != null) {
                return getSettings().getSettingFromJson(BRAND_LABEL_KEY, BrandLabel.class, defaultValue);
            }
        } catch (Exception exception) {
            Log.e(TAG, "Get BrandLabel: " + exception.getMessage());
            return defaultValue;
        }

        return null;
    }

    public static  boolean setBrandLabel(BrandLabel data, boolean replaceIfExist) {
        try {
            if(getSettings() != null) {
                getSettings().setSettingToJson(BRAND_LABEL_KEY, data, replaceIfExist);
                return true;
            }
        } catch (Exception exception) {
            Log.e(TAG, "Set BrandLabel: " + exception.getMessage());
            return false;
        }

        return false;
    }
}
