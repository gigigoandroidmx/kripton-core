package com.gigigo.kbase.presentation.utils.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Godínez Vera - 8/2/2017.
 */
public class CollectionExtensions {

    public static <T> List<T> toList(Iterable<T> iterable) {
        if(null == iterable)
            return null;

        if(iterable instanceof List) {
            return (List<T>) iterable;
        }

        ArrayList<T> list = new ArrayList<T>();

        if(iterable != null) {
            for(T t : iterable) {
                list.add(t);
            }
        }

        return list;
    }
}
