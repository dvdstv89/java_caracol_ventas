package pvc.caracol.tienda.singleton;

import java.util.List;
import java.util.function.Predicate;

public interface ISingletonList<T> {
    void add(T object);

    List<T> getList();

    T findFirst(Predicate<T> predicate);
}
