package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.observer.Observable;
import atl.stibride.repository.RepositoryException;

import java.util.List;

/**
 *
 */
public class Model extends Observable {

    private final RepoManager repoManager;
    private Ride ride = null;

    /**
     * Constructor of Model.
     *
     * @param repoManager
     */
    public Model(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    /**
     * Selects all of the stations.
     *
     * @return all of the stations as a List of Dto objects.
     * @throws RepositoryException if the resource can't be accessed.
     */
    public List<StationDto> getStations() throws RepositoryException {
        return repoManager.getAllStations();
    }

    /**
     * Sets the ride between the two stations in argument.
     *
     * @param start the starting station.
     * @param end   the end station.
     * @throws Exception if an error occurred.
     */
    public void computeRide(StationDto start, StationDto end) throws Exception {
        List<StationDto> allStations = repoManager.getAllStations();
        //List<StationDto> path = Dijkstra.computePath(allStations, start, end);
        List<StationDto> path = Dijkstra.computePath(allStations, start, end);

        ride = new Ride(start, end, path);
        notifyObservers(this);
    }

    /**
     * Gets the ride computed by the model.
     *
     * @return the ride computed my the model.
     * @throws IllegalAccessException if no ride was computed.
     */
    public Ride getRide() throws IllegalAccessException {
        if (ride == null)
            throw new IllegalAccessException("Ride is null at this moment.");
        return ride;
    }
}
