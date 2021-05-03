package atl.stibride.jdbc;

import atl.stibride.dto.DtoPair;
import atl.stibride.exceptions.RepositoryException;
import javafx.util.Pair;

import java.util.List;

public interface DaoPair<K, T extends DtoPair<K>> {

    Pair<K, K> insert(T item) throws RepositoryException;

    void delete(K firstKey, K secondKey) throws RepositoryException;

    void update(T item) throws RepositoryException;

    List<T> selectAll() throws RepositoryException;

    T select(K firstKey, K secondKey) throws RepositoryException;
}
