package com.gigigo.kbase.presentation.ui;

import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gigigo.com.kmvp.KSimpleFragment;

/**
 * @author Juan Godínez Vera - 7/5/2017.
 */
public abstract class KSimpleFragmentBase
        extends KSimpleFragment {

    private Unbinder unbinder;
    protected abstract void initializeBrandLabel();
    protected abstract void unbindBrandLabel();

    @Override
    protected void onBindView(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    protected void onUnbindView() {
        if(unbinder != null) {
            unbinder.unbind();
        }

        unbindBrandLabel();
    }

    @Override
    protected void onInitialize() {
        initializeBrandLabel();
    }
}
