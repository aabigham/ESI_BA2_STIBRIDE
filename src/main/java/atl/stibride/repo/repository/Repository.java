package atl.stibride.repo.repository;

import atl.stibride.repo.dto.Dto;
import atl.stibride.repo.exceptions.RepositoryException;

import java.util.List;

/**
 * Repository pattern to manage a resource of the application: a file, a
 * database, a web service.
 *
 * @param <K> key of an element.
 * @param <T> an element.
 * @author jlc
 */
public interface Repository<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     * @throws RepositoryException if the repository can't access to the elements.
     */
    List<T> getAll() throws RepositoryException;
}
