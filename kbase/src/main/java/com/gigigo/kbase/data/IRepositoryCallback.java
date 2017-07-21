package com.gigigo.kbase.data;

/**
 * Contract for repository callback
 *
 * @author Juan Godínez Vera - 7/3/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IRepositoryCallback<T> {
    void onSuccess(T data);
    void onError(Throwable exception);
    void onUnauthorized(Throwable exception);
}
