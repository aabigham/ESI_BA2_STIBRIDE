package atl.stibride.jdbc;

import atl.stibride.dto.FavoriteDto;
import atl.stibride.exceptions.RepositoryException;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        // TODO
        return null;
    }

    @Override
    public void delete(Integer firstKey, Integer secondKey) throws RepositoryException {
        // TODO
    }

    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        // TODO
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
        // TODO
        return null;
    }
}
