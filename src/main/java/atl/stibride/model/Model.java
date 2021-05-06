package atl.stibride.model;

import atl.stibride.jdbc.RepoManager;
import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import atl.stibride.model.dijkstra.Dijkstra;
import atl.stibride.model.validation.StationValidation;
import atl.stibride.observer.Observable;

import java.util.List;

/**
 * The model class in the MVP Pattern.
 * <p>
 * This class defines the data to be displayed
 * or otherwise acted upon in the user interface.
 */
public class Model extends Observable {

    /* Manages the database requests */
    private final RepoManager repoManager;
    /* Every metro station available */
    private List<StationDto> allStations = null;
    /* Every favorite station */
    private List<FavoriteDto> allFavorites = null;
    /* The computed ride */
    private Ride ride = null;

    /**
     * Constructor of Model.
     *
     * @param repoManager the repo manager to interact with the database.
     */
    public Model(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    /**
     * Initializes the model.
     *
     * @throws RepositoryException if there was a database error.
     */
    public void initialize() throws RepositoryException {
        allStations = repoManager.getAllStations();
        allFavorites = repoManager.getAllFavorites();
    }

    /*============================*/
    /*=========== RIDE ===========*/
    /*============================*/

    /**
     * Computes the ride between two stations.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @throws IllegalArgumentException if the two stations are the same.
     */
    public void computeRide(StationDto origin, StationDto destination) throws IllegalArgumentException {
        StationValidation.validateStations(origin, destination);
        List<StationDto> path = Dijkstra.computePath(allStations, origin, destination);
        ride = new Ride(origin, destination, path);
        notifyObservers(this);
    }

    /**
     * Getters of the computed ride.
     *
     * @return the computed ride.
     */
    public Ride getRide() {
        return ride;
    }

    /*============================*/
    /*========= STATIONS =========*/
    /*============================*/

    /**
     * Gets every metro stations as a list.
     *
     * @return every metro stations, as a List.
     * @throws RepositoryException if there was a database error.
     */
    public List<StationDto> getAllStations() throws RepositoryException {
        return allStations;
    }

    /**
     * Gets a metro station according to its key in parameter.
     *
     * @param stationKey the station key.
     * @return the wanted metro station.
     */
    public StationDto getStationDto(int stationKey) {
        return allStations.stream()
                .filter(stationDto -> stationDto.getKey().equals(stationKey))
                .findAny()
                .orElseThrow();
    }

    /*============================*/
    /*========= FAVORITES ========*/
    /*============================*/

    /**
     * Gets all the favorites stations of the user.
     *
     * @return all the favorites stations of the user.
     * @throws RepositoryException if there was a database error.
     */
    public List<FavoriteDto> getAllFavorites() throws RepositoryException {
        return allFavorites;
    }

    /**
     * Adds a Ride to the user's favorite.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @param name        the name of the favorite.
     * @throws RepositoryException if there was a database error.
     */
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

    /**
     * Removes a favorite ride from the application.
     *
     * @param origin      the origin station id.
     * @param destination the destination station id.
     * @throws RepositoryException if there was a database error.
     */
    public void removeFavorite(Integer origin, Integer destination) throws RepositoryException {
        repoManager.removeFavorite(origin, destination);
        allFavorites = repoManager.getAllFavorites(); // Change ?
        notifyObservers(this);
    }

    /**
     * Gets the name of a favorite ride.
     *
     * @param origin      the origin station id.
     * @param destination the destination station id.
     * @return the name of a favorite ride.
     * @throws RepositoryException if there was a database error.
     */
    public String getFavName(Integer origin, Integer destination) throws RepositoryException {
        return repoManager.getFavName(origin, destination);
    }
}
