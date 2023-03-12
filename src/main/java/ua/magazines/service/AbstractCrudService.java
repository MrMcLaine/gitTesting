package ua.magazines.service;

import java.util.List;

public interface AbstractCrudService<T> {
    T create(T t);

    T read(Integer id);

    default T update(T t) {
        return t;
    }

    void delete(Integer id);

    List<T> readAll();
}
