package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.observer.Observable;
import atl.stibride.repository.RepositoryException;

import java.util.List;

public class Model extends Observable {

    private final RepoManager repoManager;
    private List<StationDto> allStations = null;
    private Ride ride = null;

    public Model(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    public void initialize() throws RepositoryException {
        allStations = repoManager.getAllStations();
    }

    public void computeRide(StationDto start, StationDto end) throws IllegalArgumentException {
        if (start.equals(end)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
        List<StationDto> path = Dijkstra.computePath(allStations, start, end);
        ride = new Ride(start, end, path);
        notifyObservers(this);
    }

    public List<StationDto> getAllStations() throws RepositoryException {
        return allStations;
    }

    public Ride getRide() throws IllegalAccessException {
        if (ride == null)
            throw new IllegalAccessException("Ride is null at this moment.");
        return ride;
    }
}
