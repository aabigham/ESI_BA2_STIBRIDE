package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.repository.RepositoryException;
import atl.stibride.repository.StationRepository;
import atl.stibride.repository.StopRepository;

import java.util.List;

public class RepoManager {

    private StationRepository stationRepo;
    private StopRepository stopRepository;

    public RepoManager() {
        try {
            this.stationRepo = new StationRepository();
            this.stopRepository = new StopRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects all of the stations.
     *
     * @return all of the stations as a List of Dto objects.
     * @throws RepositoryException if the resource can't be accessed.
     */
    public List<StationDto> getAllStations() throws RepositoryException {
        return stationRepo.getAll();
    }

    /**
     * Select one station according to the key in parameter.
     *
     * @param key key of the element to select.
     * @return the selected station as a Dto object.
     * @throws RepositoryException if the resource can't be accessed.
     */
    public StationDto getStation(Integer key) throws RepositoryException {
        return stationRepo.get(key);
    }
}