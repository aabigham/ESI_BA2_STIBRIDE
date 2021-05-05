package atl.stibride.repo.repository;

import atl.stibride.repo.dto.DtoPair;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;

import java.util.List;

/**
 * Repository pattern to manage a resource of the application: a file, a
 * database, a web service.
 * Two keys version.
 *
 * @param <K> the key type.
 * @param <T> the element.
 */
public interface RepositoryPair<K, T extends DtoPair<K>> {

    /**
     * Add an element to the repository.If the element exists, the repository
     * updates this element.
     *
     * @param item the element to add.
     * @return the element's keys, useful when the key is auto-generated.
     * @throws RepositoryException if the repository can't access to the element.
     */
    Pair<K, K> add(T item) throws RepositoryException;

    /**
     * Removes the element of the specific keys.
     *
     * @param firstKey  the first key of the element to remove.
     * @param secondKey the second key of the element to remove.
     * @throws RepositoryException if the repository can't access to the element.
     */
    void remove(K firstKey, K secondKey) throws RepositoryException;

    /**
     * Returns all the elements of the repository.
     *
     * @throws RepositoryException if the repository can't access to the elements.
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Return the element of the repository with the specific key.
     *
     * @param firstKey  the first key of the element to get.
     * @param secondKey the second key of the element to get.
     * @return the element of the repository with the specific key.
     * @throws RepositoryException if the repository can't access to the element.
     */
    T get(K firstKey, K secondKey) throws RepositoryException;

    /**
     * Returns true if the element exist in the repository and false otherwise.
     * An element is found by this key.
     *
     * @param firstKey  the first key of the element to get.
     * @param secondKey the second key of the element to get.
     * @return true if the element exist in the repository and false otherwise.
     * @throws RepositoryException if the repository can't access to the element.
     */
    boolean contains(K firstKey, K secondKey) throws RepositoryException;
}
