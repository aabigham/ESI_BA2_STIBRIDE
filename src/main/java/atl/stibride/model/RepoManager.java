package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.exceptions.RepositoryException;
import atl.stibride.repository.FavoriteRepository;
import atl.stibride.repository.StationRepository;

import java.util.List;

public class RepoManager {

    private StationRepository stationRepo;
    private FavoriteRepository favoriteRepository;

    public RepoManager() {
        try {
            this.stationRepo = new StationRepository();
            this.favoriteRepository = new FavoriteRepository();
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
