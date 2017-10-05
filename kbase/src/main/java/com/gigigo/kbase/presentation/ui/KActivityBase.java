package com.gigigo.kbase.presentation.ui;

import android.content.pm.ActivityInfo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gigigo.kbase.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gigigo.com.kmvp.KActivity;

/**
 * @author Juan Godínez Vera - 7/3/2017.
 */
public abstract class KActivityBase
        extends KActivity {

    public MaterialDialog lockScreenProgressDialog;
    private Unbinder unbinder;
    protected void initializeBrandLabel(){};
    protected void unbindBrandLabel(){};

    @Override
    protected void onInitialize() {
        /*int orientationResourceValue = getResources().getInteger(R.integer.screen_orientation);
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
        }*/

        lockScreenProgressDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .build();

        /*setRequestedOrientation(screenOrientation);*/

        initToolbar();
        initializeBrandLabel();
    }

    public void showLockScreenProgressDialog(boolean active) {
        if(null == lockScreenProgressDialog) return;

        if(active && !lockScreenProgressDialog.isShowing()) {
            lockScreenProgressDialog.show();
        } else {
            lockScreenProgressDialog.dismiss();
        }
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
