package dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    List<T> getAll();

    T get(int id);

    void insert(T t);

    boolean update(int id, T t);

    boolean delete(T t);
}
