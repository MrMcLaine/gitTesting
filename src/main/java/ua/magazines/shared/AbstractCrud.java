package ua.magazines.shared;

import java.util.List;

public interface AbstractCrud<T> {

    T create(T t);

    T read(Integer id);

    default T update(T t) {
        return t;
    }

    void delete(Integer id);

    List<T> readAll();
}
