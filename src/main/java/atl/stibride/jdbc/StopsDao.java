package atl.stibride.jdbc;

import atl.stibride.dto.StopDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.*;
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
        throw new RepositoryException("2 keys are necessary");
    }

    public StopDto select(Integer key, Integer id_station) throws RepositoryException {
        if (key == null || id_station == null) {
            throw new RepositoryException("Keys cannot be null.");
        }
        String query = "SELECT id_line, id_station, id_order " +
                "FROM STOPS " +
                "WHERE id_line = ? AND id_station = ?";
        StopDto dto = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, key);
            pstmt.setInt(2, id_station);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );
                ++count;
            }
            if (count > 1) {
                throw new RepositoryException("Non unique record : " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StopsDaoHolder {
        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}