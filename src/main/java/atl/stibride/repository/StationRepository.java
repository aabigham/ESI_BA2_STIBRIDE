package atl.stibride.repository;

import atl.stibride.dto.StationDto;
import atl.stibride.jdbc.StationsDao;

import java.util.List;

public class StationRepository implements Repository<Integer, StationDto> {

    private final StationsDao dao;

    public StationRepository(StationsDao dao) {
        this.dao = dao;
    }

    public StationRepository() throws RepositoryException {
        this.dao = StationsDao.getInstance();
    }

    @Override

    public List<StationDto> getAll() throws RepositoryException {
        return null;
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        return null;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return false;
    }
}
