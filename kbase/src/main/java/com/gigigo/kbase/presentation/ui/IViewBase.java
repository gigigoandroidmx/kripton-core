package com.gigigo.kbase.presentation.ui;

import gigigo.com.kmvp.IView;

/**
 * Represents an extended {@link IView}
 *
 * @author Juan Godínez Vera - 5/31/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IViewBase
        extends IView {
    void showError(Throwable exception);
}