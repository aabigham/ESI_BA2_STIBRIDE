package atl.stibride.repository;

import atl.stibride.dto.StopDto;

import java.util.List;

public class StopRepository implements Repository<Integer, StopDto> {
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
