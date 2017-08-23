package com.gigigo.kbase.presentation.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.gigigo.kbase.R;
import com.gigigo.kbase.presentation.widget.ContentLoadingProgressBar;
import com.gigigo.kbase.presentation.widget.SectionProgressLoaderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gigigo.com.kmvp.KSimpleFragment;

/**
 * @author Juan Godínez Vera - 7/5/2017.
 */
public abstract class KSimpleFragmentBase
        extends KSimpleFragment {

    protected IFragmentListener fragmentListener;

    private Unbinder unbinder;
    protected void initializeBrandLabel(){};
    protected void unbindBrandLabel(){};

    @Nullable
    SectionProgressLoaderView sectionProgressLoader;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.context instanceof IFragmentListener) {
            fragmentListener = (IFragmentListener) context;
        }
    }

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
        sectionProgressLoader = (SectionProgressLoaderView) getView().findViewById(R.id.custom_progress_loader);
        initializeBrandLabel();
    }

    public void showSectionProgressLoader(String message) {
        if (sectionProgressLoader == null)
            return;

        if (!sectionProgressLoader.isVisible()) {
            sectionProgressLoader.show(message);
        }
    }

    public void updateSectionProgresLoaderMessage(String message) {
        if (sectionProgressLoader == null)
            return;

        if (sectionProgressLoader.isVisible()) {
            sectionProgressLoader.updateMessage(message);
        }
    }

    public void hideProgressLoader() {
        if (sectionProgressLoader != null)
            sectionProgressLoader.hide();
    }

    public ContentLoadingProgressBar getLoaderProgressBarControl() {
        if (sectionProgressLoader != null) {
            return sectionProgressLoader.getProgressBar();
        }

        return null;
    }
}
