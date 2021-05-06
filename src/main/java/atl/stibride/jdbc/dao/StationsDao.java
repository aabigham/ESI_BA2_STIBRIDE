package atl.stibride.jdbc.dao;

import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object of the station table in the database.
 */
public class StationsDao implements Dao<Integer, StationDto> {

    /**
     * Holder of the dao.
     */
    private static class StationsDaoHolder {
        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }

    /* The connection to the database */

    private final Connection connection;

    /**
     * Constructor of StationsDao.
     *
     * @throws RepositoryException if there was a database error.
     */
    public StationsDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    /**
     * Gets the instance of this dao.
     *
     * @return the instance of this dao.
     * @throws RepositoryException if there was a database error.
     */
    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    // Methods

    /**
     * Selects all of the stations inside the database.
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

        String query = "SELECT STA.id, STA.name, STO.id_line, STO.id_order " +
                "FROM STATIONS STA " +
                "JOIN STOPS STO on STA.id = STO.id_station " +
                "ORDER BY STA.name ASC";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
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
                for (StationDto currStation : dtos) {
                    // Add neighbors to every station
                    for (Pair<Integer, Integer> lines : currStation.getLinesAsList()) {
                        if (lines.getKey() == id_line
                                && ((lines.getValue() - 1 == id_order) || (lines.getValue() + 1 == id_order))) {
                            if (!currStation.getNeighbors().contains(key)) {
                                currStation.addNeighbor(key);
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
}