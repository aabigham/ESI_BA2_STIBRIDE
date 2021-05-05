package atl.stibride.repo.dao;

import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object of the favorite table in the database.
 */
public class FavoritesDao implements DaoPair<Integer, FavoriteDto> {

    /**
     * Holder of the dao.
     */
    private static class FavoriteDaoHolder {
        private static FavoritesDao getInstance() throws RepositoryException {
            return new FavoritesDao();
        }
    }

    /* The connection to the database */
    private final Connection connection;

    /**
     * Constructor of FavoriteDao.
     *
     * @throws RepositoryException if there was a database error.
     */
    public FavoritesDao() throws RepositoryException {
        this.connection = DBManager.getInstance().getConnection();
    }

    /**
     * Gets the instance of this dao.
     *
     * @return the instance of this dao.
     * @throws RepositoryException if there was a database error.
     */
    public static FavoritesDao getInstance() throws RepositoryException {
        return FavoritesDao.FavoriteDaoHolder.getInstance();
    }

    // Methods

    /**
     * Inserts a row to the favorite table inside the database.
     *
     * @param item the item to insert inside the database.
     * @return the key of the newly insterted item.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public Pair<Integer, Integer> insert(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Parameter cannot be null.");
        }
        String query = "INSERT INTO FAVORITES(start_station, end_station, name) VALUES(?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, item.getFirstKey());
            pstmt.setInt(2, item.getSecondKey());
            pstmt.setString(3, item.getName());
            pstmt.executeUpdate();

            // generated keys useless here
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return new Pair<>(item.getFirstKey(), item.getSecondKey());
    }

    /**
     * Deletes a row from the favorite table inside the database.
     *
     * @param firstKey  the first key of the favorite object.
     * @param secondKey the second key of the favorite object.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public void delete(Integer firstKey, Integer secondKey) throws RepositoryException {
        if (firstKey == null || secondKey == null) {
            throw new RepositoryException("Parameters cannot be null.");
        }
        String sql = "DELETE FROM FAVORITES WHERE start_station = ? AND end_station = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, firstKey);
            pstmt.setInt(2, secondKey);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Updates a row of the favorite table inside the database.
     *
     * @param item the item to update.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Parameter cannot be null");
        }
        String sql = "UPDATE FAVORITES " +
                "SET start_station = ?, end_station = ?, name = ? " +
                "WHERE start_station = ? AND end_station = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, item.getFirstKey());
            pstmt.setInt(2, item.getSecondKey());
            pstmt.setString(3, item.getName());
            pstmt.setInt(4, item.getFirstKey());
            pstmt.setInt(5, item.getSecondKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    /**
     * Selects every row inside the favorite table in the database.
     *
     * @return every row inside the favorite table in the database,
     * as a list of data transfer objects.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        // Return
        List<FavoriteDto> dtos = new ArrayList<>();

        String query = "SELECT start_station, end_station, name FROM FAVORITES ORDER BY name ASC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int startId = rs.getInt(1);
                int endId = rs.getInt(2);
                String name = rs.getString(3);
                dtos.add(new FavoriteDto(startId, endId, name));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    /**
     * Selects a favorite item from the table inside the database.
     *
     * @param firstKey  the first key of the favorite object.
     * @param secondKey the the second key of the favorite object.
     * @return the favorite item according to its keys in parameter.
     * @throws RepositoryException if there was a database error.
     */
    @Override
    public FavoriteDto select(Integer firstKey, Integer secondKey) throws RepositoryException {
        if (firstKey == null || secondKey == null) {
            throw new RepositoryException("Parameters cannot be null.");
        }
        String sql = "SELECT start_station, end_station, name " +
                "FROM FAVORITES " +
                "WHERE start_station = ? AND end_station = ?";
        FavoriteDto dto = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, firstKey);
            pstmt.setInt(2, secondKey);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new FavoriteDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3)
                );
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non unique record " + firstKey + ", " + secondKey);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    /**
     * Selects the name of a favorite according to its keys in parameter.
     *
     * @param firstKey  the first key of the favorite object.
     * @param secondKey the second key of the favorite object.
     * @return the name of a favorite according to its keys in parameter.
     * @throws RepositoryException if there was a database error.
     */
    public String selectNameById(Integer firstKey, Integer secondKey) throws RepositoryException {
        if (firstKey == null || secondKey == null) {
            throw new RepositoryException("Parameters cannot be null.");
        }
        String sql = "SELECT name " +
                "FROM FAVORITES " +
                "WHERE start_station = ? AND end_station = ?";
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, firstKey);
            pstmt.setInt(2, secondKey);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                name = rs.getString(1);
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non unique record " + firstKey + ", " + secondKey);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return name;
    }
}
