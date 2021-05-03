package atl.stibride.repo.dao;

import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.exceptions.RepositoryException;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritesDao implements DaoPair<Integer, FavoriteDto> {

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

    // Methods

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

    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Parameter cannot be null");
        }
        String sql = "UPDATE FAVORITES " +
                "SET start_station = ?, end_station = ?, name = ? " +
                "WHERE start_station=? AND end_station = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, item.getFirstKey());
            pstmt.setInt(2, item.getSecondKey());
            pstmt.setString(3, item.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        // Return
        List<FavoriteDto> dtos = new ArrayList<>();

        String query = "SELECT start_station, end_station, name FROM FAVORITES";

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
}
