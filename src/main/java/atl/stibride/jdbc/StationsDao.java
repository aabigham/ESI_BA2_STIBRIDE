package atl.stibride.jdbc;

import atl.stibride.dto.StationDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationsDao implements Dao<Integer, StationDto> {

    private final Connection connection;

    public StationsDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    /**
     * Selects all of the stations.
     *
     * @return all of the stations as a List of Dto objects.
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Override

    public List<StationDto> selectAll() throws RepositoryException {
        List<StationDto> dtos = new ArrayList<>();
        String query = "SELECT id, name FROM STATIONS";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                StationDto dto = new StationDto(
                        rs.getInt(1),
                        rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    /**
     * Select one station according to the key in parameter.
     *
     * @param key key of the element to select.
     * @return the selected station as a Dto object.
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Key cannot be null.");
        }
        String query = "SELECT id, name FROM STATIONS WHERE  id = ?";
        StationDto dto = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(
                        rs.getInt(1),
                        rs.getString(2));
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

    /**
     * Gets the name of a station according to its key.
     *
     * @param key of the element to select.
     * @return the name of a station, according to its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    public String getStationName(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Key cannot be null.");
        }
        String query = "SELECT name FROM STATIONS WHERE  id = ?";
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                name = rs.getString(1);
                ++count;
            }
            if (count > 1) {
                throw new RepositoryException("Non unique record : " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return name;
    }

    private static class StationsDaoHolder {
        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }
}