package atl.stibride.model;

import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.dto.StationDto;
import atl.stibride.repo.exceptions.RepositoryException;
import atl.stibride.repo.repository.FavoriteRepository;
import atl.stibride.repo.repository.StationRepository;

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

    /* STATIONS */

    public List<StationDto> getAllStations() throws RepositoryException {
        return stationRepo.getAll();
    }

    public StationDto getStation(Integer key) throws RepositoryException {
        return stationRepo.get(key);
    }

    /* FAVORITES */

    public List<FavoriteDto> getAllFavorites() throws RepositoryException {
        return favoriteRepository.getAll();
    }

    public void addFavorite(Integer firstKey, Integer secondKey, String name) throws RepositoryException {
        favoriteRepository.add(new FavoriteDto(firstKey, secondKey, name));
    }

    public void removeFavorite(Integer origin, Integer destination) throws RepositoryException {
        favoriteRepository.remove(origin, destination);
    }

    public String getFavName(Integer origin, Integer destination) throws RepositoryException {
        return favoriteRepository.getFavoriteNameById(origin, destination);
    }
}
