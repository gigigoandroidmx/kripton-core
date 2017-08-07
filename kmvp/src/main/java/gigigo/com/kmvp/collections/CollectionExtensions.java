package gigigo.com.kmvp.collections;

import java.util.ArrayList;
import java.util.Collection;
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

    public static <T> T find(final Collection<T> collection, final IPredicate<T> predicate) {
        if(null == collection || collection.isEmpty()) return null;

        for (T item : collection){
            if (predicate.apply(item)){
                return item;
            }
        }

        return null;
    }
}
