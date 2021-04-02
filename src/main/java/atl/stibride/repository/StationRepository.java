package atl.stibride.repository;

import atl.stibride.dto.StationDto;

import java.util.List;

public class StationRepository implements Repository<Integer, StationDto> {

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
