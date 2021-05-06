package atl.stibride.jdbc.dao;

import atl.stibride.jdbc.dto.Dto;
import atl.stibride.jdbc.exceptions.RepositoryException;

import java.util.List;

/**
 * Data access object of a resource (file, database, web service).
 *
 * @param <K> key of an item.
 * @param <T> item of the resource.
 * @author jlc
 * @see <a href="https://en.wikipedia.org/wiki/Data_access_object"> Wikipedia</a>
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws RepositoryException;

}
