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
        return dao.selectAll();
    }

    @Override
    public StopDto get(Integer key) throws RepositoryException {
        return dao.select(key);
    }
}
