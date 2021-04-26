package atl.stibride.jdbc;

import atl.stibride.dto.StationDto;
import atl.stibride.repository.Dao;
import atl.stibride.repository.RepositoryException;
import javafx.util.Pair;

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
        // Cant reset the result set head so i have to store the datas here...
        List<Integer[]> datas = new ArrayList<>();

        // Return
        List<StationDto> dtos = new ArrayList<>();

        String queryStations = "SELECT STA.id, STA.name, STO.id_line, STO.id_order " +
                "FROM STATIONS STA " +
                "JOIN STOPS STO on STA.id = STO.id_station " +
                "ORDER BY STA.name ASC";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(queryStations);
            while (rs.next()) {
                int key = rs.getInt(1);
                String name = rs.getString(2);
                int id_line = rs.getInt(3);
                int id_order = rs.getInt(4);

                // For the neighbor use
                datas.add(new Integer[]{key, id_line, id_order});

                // Add line to stations
                boolean contains = false;
                for (StationDto stationDto : dtos) {
                    if (stationDto.getKey() == key) {
                        contains = true;
                        stationDto.addLine(id_line, id_order);
                        break;
                    }
                }
                if (!contains) {
                    StationDto dto = new StationDto(key, name);
                    dto.addLine(id_line, id_order);
                    dtos.add(dto);
                }
            }
            // rs.beforeFirst();
            // Neighbors
            for (Integer[] row : datas) {
                int key = row[0];
                int id_line = row[1];
                int id_order = row[2];
                // Add neighbors to every station
                for (StationDto stationDto : dtos) {
                    for (Pair<Integer, Integer> lines : stationDto.getLinesAsList()) {
                        if (lines.getKey() == id_line
                                && ((lines.getValue() - 1 == id_order)
                                || (lines.getValue() + 1 == id_order))) {
                            if (!stationDto.getNeighbors().contains(key)) {
                                stationDto.addNeighbor(key);
                            }
                        }
                    }
                }
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
        String query = "SELECT STA.id, STA.name, STO.id_line, STO.id_order " +
                "FROM STATIONS STA " +
                "JOIN STOPS STO on STA.id = STO.id_station " +
                "WHERE id = ? " +
                "ORDER BY STA.name ASC";
        StationDto dto = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            dto = new StationDto(id, name);
            int id_line = rs.getInt(3);
            int id_order = rs.getInt(4);
            dto.addLine(id_line, id_order);

            int count = 0;
            while (rs.next()) {
                dto.addLine(rs.getInt(3), rs.getInt(4));
                if (id == rs.getInt(1)) {
                    ++count;
                }
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