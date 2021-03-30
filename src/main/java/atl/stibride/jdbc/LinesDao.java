package atl.stibride.jdbc;

import atl.stibride.dto.LineDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.Connection;
import java.util.List;

public class LinesDao implements Dao<Integer, LineDto> {

    private final Connection connection;

    public LinesDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    @Override
    public List<LineDto> selectAll() throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public LineDto select(Integer key) throws RepositoryException {
        // TODO
        return null;
    }
}
