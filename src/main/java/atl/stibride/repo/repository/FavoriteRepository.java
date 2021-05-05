package atl.stibride.repo.repository;

import atl.stibride.repo.dao.FavoritesDao;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;

import java.util.List;

public class FavoriteRepository implements RepositoryPair<Integer, FavoriteDto> {

    private final FavoritesDao dao;

    public FavoriteRepository(FavoritesDao dao) {
        this.dao = dao;
    }

    public FavoriteRepository() throws RepositoryException {
        this.dao = FavoritesDao.getInstance();
    }

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

    @Override
    public void remove(Integer firstKey, Integer secondKey) throws RepositoryException {
        dao.delete(firstKey, secondKey);
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(Integer firstKey, Integer secondKey) throws RepositoryException {
        return dao.select(firstKey, secondKey);
    }

    @Override
    public boolean contains(Integer firstKey, Integer secondKey) throws RepositoryException {
        FavoriteDto refreshItem = dao.select(firstKey, secondKey);
        return refreshItem != null;
    }

    public String getFavoriteNameById(Integer origin, Integer destination) throws RepositoryException {
        return dao.selectNameById(origin, destination);
    }
}
