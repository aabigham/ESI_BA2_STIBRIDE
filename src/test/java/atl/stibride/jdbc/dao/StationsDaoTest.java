package atl.stibride.jdbc.dao;

import atl.stibride.config.ConfigManager;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class runs the tests for the stations dao.
 */
class StationsDaoTest {

    private StationsDao instance;
    private static final int NB_STATIONS = 60;

    public StationsDaoTest() {
        try {
            ConfigManager.getInstance().load();
            instance = StationsDao.getInstance();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checking the size of the request which should be 60.
     *
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Test
    void testSelectAllSize() throws RepositoryException {
        // Arrange
        List<StationDto> stations = instance.selectAll();
        // Action
        int expectedSize = stations.size();
        // Assert
        assertEquals(expectedSize, NB_STATIONS);
    }

    /**
     * Checking if every station has at least one neighbor.
     *
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Test
    void testSelectAllNeighborsNotZero() throws RepositoryException {
        // Arrange
        List<StationDto> stations = instance.selectAll();
        boolean atLeastOneNeighbor = true;
        // Action
        for (StationDto stationDto : stations) {
            if (stationDto.getNeighbors().size() == 0) {
                atLeastOneNeighbor = false;
                break;
            }
        }
        // Assert
        assertTrue(atLeastOneNeighbor);
    }

    /**
     * Checking if every station has no duplicate neighbor.
     *
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Test
    void testSelectAllNeighborsNoDuplicate() throws RepositoryException {
        // Arrange
        List<StationDto> stations = instance.selectAll();
        boolean noDuplicate = true;
        // Action
        for (StationDto stationDto : stations) {
            // A set contains no duplicate
            Set<Integer> set = new HashSet<>(stationDto.getNeighbors());
            if (set.size() < stationDto.getNeighbors().size()) {
                noDuplicate = false;
                break;
            }
        }
        // Assert
        assertTrue(noDuplicate);
    }
}