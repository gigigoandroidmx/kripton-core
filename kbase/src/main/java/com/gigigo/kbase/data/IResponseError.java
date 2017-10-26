package com.gigigo.kbase.data;

/**
 * @author Juan Godínez Vera - 7/21/2017.
 */
public interface IResponseError<T> {
    String getError();
    void setError(String error);

    T getErrors();
    void setErrors(T obj);
}
