package atl.stibride.repository;

import atl.stibride.dto.StopDto;
import atl.stibride.jdbc.StopsDao;

import java.util.List;

public class StopRepository implements Repository<Integer, StopDto> {

    private final StopsDao dao;

    public StopRepository(StopsDao dao) {
        this.dao = dao;
    }

    public StopRepository() throws RepositoryException {
        this.dao = StopsDao.getInstance();
    }

    @Override
    public List<StopDto> getAll() throws RepositoryException {
        return null;
    }

    @Override
    public StopDto get(Integer key) throws RepositoryException {
        return null;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return false;
    }
}
