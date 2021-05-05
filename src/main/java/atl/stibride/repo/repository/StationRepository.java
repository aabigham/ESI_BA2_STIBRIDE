package atl.stibride.repo.repository;

import atl.stibride.repo.dao.StationsDao;
import atl.stibride.repo.dto.StationDto;
import atl.stibride.repo.exceptions.RepositoryException;

import java.util.List;

public class StationRepository implements Repository<Integer, StationDto> {

    private final StationsDao dao;

    public StationRepository(StationsDao dao) {
        this.dao = dao;
    }

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
