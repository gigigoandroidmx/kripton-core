package com.gigigo.kbase.presentation.ui;

import android.content.pm.ActivityInfo;

import com.gigigo.kbase.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gigigo.com.kmvp.KActivity;

/**
 * @author Juan Godínez Vera - 7/3/2017.
 */
public abstract class KActivityBase
        extends KActivity {

    private Unbinder unbinder;
    protected abstract void initializeBrandLabel();
    protected abstract void unbindBrandLabel();

    @Override
    protected void onInitialize() {
        int orientationResourceValue = getResources().getInteger(R.integer.screen_orientation);
        int screenOrientation;
        switch (orientationResourceValue) {
            case 0:
                screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                break;
            case 1:
                screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            default:
                screenOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
                break;
        }

        setRequestedOrientation(screenOrientation);

        initToolbar();
        initializeBrandLabel();
    }

    @Override
    protected void onBindView() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onUnbindView() {
        if(unbinder != null) {
            unbinder.unbind();
        }

        unbindBrandLabel();
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }
}
