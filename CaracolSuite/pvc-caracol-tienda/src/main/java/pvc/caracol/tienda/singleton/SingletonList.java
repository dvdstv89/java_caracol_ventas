package pvc.caracol.tienda.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class SingletonList<T> implements ISingletonList<T> {

    protected List<T> list = new ArrayList<>();

    @Override
    public void add(T object) {
        list.add(object);
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public T findFirst(Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
}
