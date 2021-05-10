package atl.stibride.model;

import atl.stibride.jdbc.RepoManager;
import atl.stibride.jdbc.dto.FavoriteDto;
import atl.stibride.jdbc.dto.StationDto;
import atl.stibride.jdbc.exceptions.RepositoryException;
import atl.stibride.model.dijkstra.Dijkstra;
import atl.stibride.model.validation.StationValidation;
import atl.stibride.observer.Observable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The model class in the MVP Pattern.
 * <p>
 * This class defines the data to be displayed
 * or otherwise acted upon in the user interface.
 */
public class Model extends Observable {

    /* Allows the Observers to know that its the ride that was updated */
    private static final Integer NOTIFY_RIDE = 0;
    /* Allow the Observers to know that its the favorites that were updated */
    private static final Integer NOTIFY_FAVORITES = 1;

    /* Manages the database requests */
    private final RepoManager repoManager;
    /* Every metro station available */
    private List<StationDto> allStations;
    /* Every favorite station */
    private List<FavoriteDto> allFavorites;
    /* The computed ride */
    private Optional<Ride> rideOptional;

    /**
     * Constructor of Model. Loads the config file.
     *
     * @throws IOException if no file is found.
     */
    public Model() throws IOException {
        this.repoManager = new RepoManager();
        this.allStations = null;      // Will be set in the initialize method
        this.allFavorites = null;     // Will be set in the initialize method
        this.rideOptional = Optional.empty(); // Usually there are no ride at the first launch of the app
    }

    /**
     * Initializes the model. Loads the favorites and all the stations from the database.
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
     * This method notifies the observer.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @throws IllegalArgumentException if the two stations are the same.
     */
    public void computeRide(StationDto origin, StationDto destination) throws IllegalArgumentException {
        StationValidation.validateStations(origin, destination);
        List<StationDto> path = Dijkstra.computePath(allStations, origin, destination);
        rideOptional = Optional.of(new Ride(origin, destination, path));
        notifyObservers(NOTIFY_RIDE);
    }

    /**
     * Getters of the computed ride.
     *
     * @return the computed ride.
     */
    public Optional<Ride> getRideOptional() {
        return rideOptional;
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
     * This method notifies the observer.
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
        notifyObservers(NOTIFY_FAVORITES);
    }

    /**
     * Removes a favorite ride from the application.
     * This method notifies the observer.
     *
     * @param origin      the origin station id.
     * @param destination the destination station id.
     * @throws RepositoryException if there was a database error.
     */
    public void removeFavorite(Integer origin, Integer destination) throws RepositoryException {
        repoManager.removeFavorite(origin, destination);
        allFavorites = repoManager.getAllFavorites(); // Change ?
        notifyObservers(NOTIFY_FAVORITES);
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
