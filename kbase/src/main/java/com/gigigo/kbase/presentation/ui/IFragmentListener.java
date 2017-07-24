package com.gigigo.kbase.presentation.ui;

/**
 * Created by Omar on 7/19/17.
 */
public interface IFragmentListener<T> {
    void showError(Throwable exception);
    void showProgress(boolean active);
    void onFragmentActionComplete(T data);
}