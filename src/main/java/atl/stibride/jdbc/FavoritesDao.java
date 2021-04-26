package atl.stibride.jdbc;

import atl.stibride.dto.StationDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.Connection;
import java.util.List;

public class FavoritesDao implements Dao<Integer, StationDto> {

    private static class FavoriteDaoHolder {
        private static FavoritesDao getInstance() throws RepositoryException {
            return new FavoritesDao();
        }
    }

    private final Connection connection;

    public FavoritesDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static FavoritesDao getInstance() throws RepositoryException {
        return FavoritesDao.FavoriteDaoHolder.getInstance();
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        // TODO
        return null;
    }
}
