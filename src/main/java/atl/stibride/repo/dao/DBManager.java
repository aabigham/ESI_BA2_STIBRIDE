package atl.stibride.repo.dao;


import atl.stibride.config.ConfigManager;
import atl.stibride.repo.exceptions.RepositoryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jlc
 */
class DBManager {

    /* Connection to the database */
    private Connection connection;

    /**
     * Constructor of DBManager.
     */
    private DBManager() {
    }

    /**
     * Gets the connection to the database.
     *
     * @return the connection to the database.
     * @throws RepositoryException if there was a database error.
     */
    Connection getConnection() throws RepositoryException {
        String jdbcUrl = "jdbc:sqlite:" + ConfigManager.getInstance().getProperties("db.url");
        //|| connection.isClosed()
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                throw new RepositoryException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * Gets an instance of this database manager.
     *
     * @return an instance of this database manager.
     */
    static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    /**
     * Holder of the class instance.
     */
    private static class DBManagerHolder {

        private static final DBManager INSTANCE = new DBManager();
    }
}
