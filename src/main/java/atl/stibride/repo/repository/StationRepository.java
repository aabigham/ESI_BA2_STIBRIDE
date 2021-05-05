package atl.stibride.repo.repository;

import atl.stibride.repo.dao.StationsDao;
import atl.stibride.repo.dto.StationDto;
import atl.stibride.repo.exceptions.RepositoryException;

import java.util.List;

/**
 * Manages the stations inside the database.
 * Take cares of the logic behind the different requests.
 */
public class StationRepository implements Repository<Integer, StationDto> {

    /* The station data access object */
    private final StationsDao dao;

    /**
     * Constructor of the station repository.
     *
     * @param dao the favorite data access object.
     */
    public StationRepository(StationsDao dao) {
        this.dao = dao;
    }

    /**
     * Constructor of the station repository.
     *
     * @throws RepositoryException if there was a database error.
     */
    public StationRepository() throws RepositoryException {
        this.dao = StationsDao.getInstance();
    }

    /**
     * Selects all of the stations.
     *
     * @return all of the stations as a List of Dto objects.
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Override
    public List<StationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }
}
