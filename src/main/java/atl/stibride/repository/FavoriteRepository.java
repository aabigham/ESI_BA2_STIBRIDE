package atl.stibride.repository;

import atl.stibride.dto.StationDto;
import atl.stibride.exceptions.RepositoryException;
import atl.stibride.jdbc.FavoritesDao;

import java.util.List;

public class FavoriteRepository implements Repository<Integer, StationDto> {

    private final FavoritesDao dao;

    public FavoriteRepository(FavoritesDao dao) {
        this.dao = dao;
    }

    public FavoriteRepository() throws RepositoryException {
        this.dao = FavoritesDao.getInstance();
    }

    @Override
    public Integer add(StationDto item) throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        // TODO
    }

    @Override
    public List<StationDto> getAll() throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        // TODO
        return false;
    }
}
