package atl.stibride.repo.repository;

import atl.stibride.repo.dto.DtoPair;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;

import java.util.List;

public interface RepositoryPair<K, T extends DtoPair<K>> {

    Pair<K, K> add(T item) throws RepositoryException;

    void remove(K firstKey, K secondKey) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    T get(K firstKey, K secondKey) throws RepositoryException;

    boolean contains(K firstKey, K secondKey) throws RepositoryException;

}
