package com.gigigo.kbase.presentation.ui;

import com.gigigo.kbase.presentation.thread.IExecutor;
import com.gigigo.kbase.presentation.thread.KThreadExecutor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;

import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KPresenter;

/**
 * Created by Omar on 6/6/17.
 */
public abstract class KPresenterBase<T extends IView>
        extends KPresenter<T> {

    private KThreadExecutor threadExecutor;

    @Override
    public void onBusRegister() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onBusUnregister() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSubscriberExceptionEvent(SubscriberExceptionEvent exceptionEvent) {
        if (exceptionEvent != null) {
            handleError(exceptionEvent.throwable);
        }
    }

    public IExecutor getExecutor() {
        if (threadExecutor == null) {
            threadExecutor = new KThreadExecutor();
        }

        return threadExecutor;
    }
}
