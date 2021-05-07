package atl.stibride.jdbc.repository;

import atl.stibride.jdbc.dao.FavoritesDao;
import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import javafx.util.Pair;

import java.util.List;

/**
 * Manages the favorites inside the database.
 * Take cares of the logic behind the different requests.
 */
public class FavoriteRepository implements RepositoryPair<Integer, FavoriteDto> {

    /* The favorite data access object */
    private final FavoritesDao dao;

    /**
     * Constructor of favorite repository.
     *
     * @param dao the favorite data access object.
     */
    public FavoriteRepository(FavoritesDao dao) {
        this.dao = dao;
    }

    /**
     * Constructor of the favorite repository.
     *
     * @throws RepositoryException if there was a database error.
     */
    public FavoriteRepository() throws RepositoryException {
        this.dao = FavoritesDao.getInstance();
    }

    /**
     * Adds an item to the FAVORITES table inside the database.
     *
     * @param item the favorite item to add to the table.
     * @return the keys of the newly added item.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public Pair<Integer, Integer> add(FavoriteDto item) throws RepositoryException {
        int firstKey = item.getFirstKey();
        int secondKey = item.getSecondKey();
        Pair<Integer, Integer> pair = new Pair<>(firstKey, secondKey);
        if (contains(firstKey, secondKey)) {
            dao.update(item);
        } else {
            pair = dao.insert(item);
        }
        return pair;
    }

    /**
     * Removes a favorite from the database, according to its keys in parameter.
     *
     * @param firstKey  the first key of the favorite.
     * @param secondKey the second key of the favorite.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public void remove(Integer firstKey, Integer secondKey) throws RepositoryException {
        if (contains(firstKey, secondKey)) {
            dao.delete(firstKey, secondKey);
        } else {
            throw new RepositoryException("Cannot delete nothing");
        }
    }

    /**
     * Gets all the favorites in the database, as a List of data transfer objects.
     *
     * @return all the favorites in the database, as a List of data transfer objects.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    /**
     * Gets one favorite item according to its keys in parameter.
     *
     * @param firstKey  the first key of the favorite.
     * @param secondKey the the second key of the favorite.
     * @return the favorite item according to its keys in parameter.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public FavoriteDto get(Integer firstKey, Integer secondKey) throws RepositoryException {
        return dao.select(firstKey, secondKey);
    }

    /**
     * Checks if the database contains the favorite item,
     * according to the keys in parameter.
     *
     * @param firstKey  the first key of the favorite.
     * @param secondKey the second key of the favorite.
     * @return true if the favorite is already inside the database, false otherwise.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public boolean contains(Integer firstKey, Integer secondKey) throws RepositoryException {
        FavoriteDto refreshItem = dao.select(firstKey, secondKey);
        return refreshItem != null;
    }

    /**
     * Gets the name of the favorite ride according to its keys in parameter.
     *
     * @param firstKey  the first key of the favorite.
     * @param secondKey the second key of the favorite.
     * @return the name of the favorite ride according to its keys in parameter.
     * @throws RepositoryException if there was a database error.
     */
    public String getFavoriteNameById(Integer firstKey, Integer secondKey) throws RepositoryException {
        return dao.selectNameById(firstKey, secondKey);
    }
}
