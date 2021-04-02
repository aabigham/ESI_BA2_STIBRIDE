package atl.stibride.jdbc;

import atl.stibride.dto.StopDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        List<StopDto> dtos = new ArrayList<>();
        String query = "SELECT id_line, id_station, id_order FROM STOPS";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                StopDto dto = new StopDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
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