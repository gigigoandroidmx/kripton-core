package com.gigigo.kbase.data;

import com.gigigo.kretrofitmanager.HttpErrorHandling;
import com.gigigo.kretrofitmanager.ICallbackAdapter;
import com.gigigo.kretrofitmanager.ResponseState;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import retrofit2.Response;

/**
 * Adapter for handling responses from rest service
 *
 * @author Juan Godínez Vera - 7/4/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public class CallbackAdapter<T, R extends IResponseError>
        implements ICallbackAdapter<T> {

    private final IRepositoryCallback<T> callback;
    private final Class<R> clazz;

    public CallbackAdapter(IRepositoryCallback<T> callback, Class<R> clazz) {
        this.callback = callback;
        this.clazz = clazz;
    }

    @Override
    public void onDataEmpty() {
    }

    @Override
    public void onSuccess(T data) {
        callback.onSuccess(data);
    }

    @Override
    public void onError(Throwable exception) {
        callback.onError(exception);
    }

    @Override
    public void onUnauthorized(Response<T> response) {
        callback.onUnauthorized(getResponseState(response));
    }

    @Override
    public void onDataNotAvailable(ResponseState entryState) {
        callback.onError(entryState);
    }

    @Override
    public ResponseState handleErrorResponse(Response<T> response) {
        return getResponseState(response);
    }

    private ResponseState getResponseState(Response<T> response) {
        int code = response.code();

        String message;
        Object obj = null;

        try {
            Gson gson = new Gson();
            IResponseError responseError = gson.fromJson(response.errorBody().string(), clazz);
//            message = String.format("Error %1$d - %2$s", response.code(), responseError.getError());
            message = String.valueOf(responseError.getError());
            obj = responseError.getErrors();
        } catch (Exception e) {
            message = HttpErrorHandling.fromInt(code).toString();
        }

        return new ResponseState(message, code, obj);
    }
}