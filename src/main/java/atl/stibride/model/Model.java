package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.observer.Observable;
import atl.stibride.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;

public class Model extends Observable {

    private final RequestManager requestManager;
    private Ride ride = null;

    public Model() {
        this.requestManager = new RequestManager();
    }

    /**
     * Selects all of the stations.
     *
     * @return all of the stations as a List of Dto objects.
     * @throws RepositoryException if the resource can't be accessed.
     */
    public List<StationDto> getStations() throws RepositoryException {
        return requestManager.getAllStations();
    }

    /**
     * Computes the best path to take between the two stations.
     *
     * @param start the starting station.
     * @param end   the end station.
     */
    public void computeRide(StationDto start, StationDto end) {
        // TODO
        ride = new Ride(start, end, new ArrayList<>());
        notifyObservers(this);
    }

    public Ride getRide() throws IllegalAccessException {
        if (ride == null)
            throw new IllegalAccessException("Ride is null at this moment.");
        return ride;
    }
}
