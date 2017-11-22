package com.gigigo.kbase.presentation.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gigigo.kbase.R;
import com.gigigo.kbase.presentation.widget.ContentLoadingProgressBar;
import com.gigigo.kbase.presentation.widget.SectionProgressLoaderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gigigo.com.kmvp.IPresenter;
import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KFragment;

/**
 * Represents an extended {@link KFragment} with binding for Android views
 *
 * @author Juan Godínez Vera - 5/31/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class KFragmentBase<V extends IView, P extends IPresenter<V>>
        extends KFragment<V, P> {

    private Unbinder unbinder;
    protected MaterialDialog lockScreenProgressDialog;
    protected IFragmentListener fragmentListener;

    protected void initializeBrandLabel() {
    }

    protected void unbindBrandLabel() {
    }

    protected void onUnboxFragmentExtras(Bundle arguments) {
    }

    protected void onRestoreFragmentExtras(Bundle arguments) {
    }

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            onUnboxFragmentExtras(getArguments());
        }

        if (getArguments() != null) {
            onRestoreFragmentExtras(getArguments());
        }
    }

    @Override
    protected void onBindView(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    protected void onUnbindView() {
        unbindBrandLabel();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onInitialize() {
        sectionProgressLoader = (SectionProgressLoaderView) getView().findViewById(R.id.custom_progress_loader);
        lockScreenProgressDialog = new MaterialDialog.Builder(getContext())
                .cancelable(false)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .build();

        initializeBrandLabel();
    }

    public void showLockScreenProgressDialog(boolean active) {
        if (null == lockScreenProgressDialog) return;

        if (active && !lockScreenProgressDialog.isShowing()) {
            lockScreenProgressDialog.show();
        } else {
            lockScreenProgressDialog.dismiss();
        }
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

    public void setSectionProgressLoaderTextSize(float spSize) {
        float pixelSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, spSize, getResources().getDisplayMetrics());
        sectionProgressLoader.setMessateTextSize(pixelSize);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (automaticBusConnection()) {
            if (this.presenter != null) {
                presenter.onBusRegister();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (automaticBusConnection()) {
            if (this.presenter != null) {
                presenter.onBusUnregister();
            }
        }
    }

    protected boolean automaticBusConnection() {
        return true;
    }
}
