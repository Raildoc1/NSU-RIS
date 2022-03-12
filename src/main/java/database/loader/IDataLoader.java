package database.loader;

import java.util.List;

public interface IDataLoader<T> {
    void load(List<T> data);
}
