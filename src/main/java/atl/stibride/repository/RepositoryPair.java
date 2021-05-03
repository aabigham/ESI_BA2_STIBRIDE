package atl.stibride.repository;

import atl.stibride.dto.DtoPair;
import atl.stibride.exceptions.RepositoryException;

import java.util.List;

public interface RepositoryPair<K, T extends DtoPair<K>> {
    
    K add(T item) throws RepositoryException;

    void remove(K firstKey, K secondKey) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    T get(K firstKey, K secondKey) throws RepositoryException;

    boolean contains(K firstKey, K secondKey) throws RepositoryException;

}
