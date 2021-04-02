package atl.stibride.jdbc;

import atl.stibride.dto.StopDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.Connection;
import java.util.List;

public class StopsDao implements Dao<Integer, StopDto> {

    private final Connection connection;

    public StopsDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }

    @Override
    public List<StopDto> selectAll() throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public StopDto select(Integer key) throws RepositoryException {
        // TODO
        return null;
    }

    private static class StopsDaoHolder {
        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}