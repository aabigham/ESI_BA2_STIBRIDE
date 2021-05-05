package atl.stibride.model;

import atl.stibride.model.dijkstra.Dijkstra;
import atl.stibride.observer.Observable;
import atl.stibride.repo.dto.FavoriteDto;
import atl.stibride.repo.dto.StationDto;
import atl.stibride.repo.exceptions.RepositoryException;

import java.util.List;

public class Model extends Observable {

    private final RepoManager repoManager;
    private List<StationDto> allStations = null;
    private List<FavoriteDto> allFavorites = null;
    private Ride ride = null;

    public Model(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    public void initialize() throws RepositoryException {
        allStations = repoManager.getAllStations();
        allFavorites = repoManager.getAllFavorites();
    }

    /*============================*/
    /*=========== RIDE ===========*/
    /*============================*/

    public void computeRide(StationDto origin, StationDto destination) throws IllegalArgumentException {
        StationValidation.validateStations(origin, destination);
        List<StationDto> path = Dijkstra.computePath(allStations, origin, destination);
        ride = new Ride(origin, destination, path);
        notifyObservers(this);
    }

    public Ride getRide() throws IllegalAccessException {
        return ride;
    }

    /*============================*/
    /*========= STATIONS =========*/
    /*============================*/

    public List<StationDto> getAllStations() throws RepositoryException {
        return allStations;
    }

    public StationDto getStationDto(int stationKey) {
        return allStations.stream()
                .filter(stationDto -> stationDto.getKey().equals(stationKey))
                .findAny()
                .orElseThrow();
    }

    /*============================*/
    /*========= FAVORITES ========*/
    /*============================*/

    public List<FavoriteDto> getAllFavorites() throws RepositoryException {
        return allFavorites;
    }

    public void addToFavorite(StationDto origin, StationDto destination, String name)
            throws RepositoryException {
        StationValidation.validateStations(origin, destination);
        repoManager.addFavorite(
                origin.getKey(),
                destination.getKey(),
                name
        );
        allFavorites = repoManager.getAllFavorites(); // Change ?
        notifyObservers(this);
    }

    public void removeFavorite(Integer origin, Integer destination) throws RepositoryException {
        repoManager.removeFavorite(origin, destination);
        allFavorites = repoManager.getAllFavorites(); // Change ?
        notifyObservers(this);
    }

    public String getFavName(Integer origin, Integer destination) throws RepositoryException {
        return repoManager.getFavName(origin, destination);
    }
}
