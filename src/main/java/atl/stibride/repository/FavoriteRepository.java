package atl.stibride.repository;

import atl.stibride.dto.FavoriteDto;
import atl.stibride.exceptions.RepositoryException;
import atl.stibride.jdbc.FavoritesDao;

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
    public Integer add(FavoriteDto item) throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public void remove(Integer firstKey, Integer secondKey) throws RepositoryException {
        // TODO
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(Integer firstKey, Integer secondKey) throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public boolean contains(Integer firstKey, Integer secondKey) throws RepositoryException {
        // TODO
        return false;
    }
}
