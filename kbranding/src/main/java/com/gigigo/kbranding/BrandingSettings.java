package com.gigigo.kbranding;

import android.content.Context;

/**
 * @author Juan Godínez Vera - 24/07/2017.
 */

public class BrandingSettings {

    private Context context;
    private String endPoint;

    public BrandingSettings setContext(Context context) {
        this.context = context;
        return this;
    }

    public BrandingSettings setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
