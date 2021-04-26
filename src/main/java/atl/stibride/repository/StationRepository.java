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

    /**
     * Select one station according to the key in parameter.
     *
     * @param key key of the element to select.
     * @return the selected station as a Dto object.
     * @throws RepositoryException if the resource can't be accessed.
     */
    @Override
    public StationDto get(Integer key) throws RepositoryException {
        return dao.select(key);
    }


}
