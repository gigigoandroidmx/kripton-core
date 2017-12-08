package com.gigigo.kbase.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import gigigo.com.kmvp.IPresenter;
import gigigo.com.kmvp.IView;

/**
 * @author Omar - 7/14/17.
 */
public abstract class KActivityBasePresenter<V extends IView, P extends IPresenter>
        extends KActivityBase {

    protected P presenter;
    protected abstract P createPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onBusRegister();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onBusUnregister();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();

        if(presenter == null)
            throw new NullPointerException("The presenter must not be null.");

        presenter.attachView((V) this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeBrandLabel() {

    }

    @Override
    protected int getLayoutResourceId() {
        return 0;
    }
}
