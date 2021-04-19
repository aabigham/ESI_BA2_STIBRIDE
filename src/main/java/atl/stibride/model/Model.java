package atl.stibride.model;

import atl.stibride.dto.StationDto;
import atl.stibride.observer.Observable;
import atl.stibride.repository.RepositoryException;

import java.util.*;

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
        List<StationDto> path = computePath(start, end);
        ride = new Ride(start, end, path);
        notifyObservers(this);
    }

    /**
     * Computes the best path to take between the two stations.
     *
     * @param start the starting station.
     * @param end   the end station.
     * @return the best path to take between the two stations.
     * @throws Exception if an error occurred.
     */
    private List<StationDto> computePath(StationDto start, StationDto end) throws Exception {
        // TODO better algorithm
        if (start.equals(end))
            throw new IllegalArgumentException("Start station and destination station must be different.");

        List<StationDto> allStations = repoManager.getAllStations();
        int id_start = start.getKey();
        int id_end = end.getKey();

        List<StationDto> path = new ArrayList<>();
        path.add(start);
        Map<Integer, Boolean> visited = new HashMap<>();

        List<StationDto> queue = new LinkedList<>();

        visited.put(id_start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            StationDto currDto = queue.remove(0);

            Iterator<Integer> it = currDto.getNeighbors().listIterator();
            while (it.hasNext()) {
                Integer currNeighborKey = it.next();
                StationDto currNeighborDto = allStations.stream()
                        .filter(stationDto -> stationDto.getKey().equals(currNeighborKey))
                        .findAny()
                        .orElseThrow();

                if (!visited.containsKey(currNeighborKey)) {
                    visited.put(currNeighborKey, true);
                    path.add(currNeighborDto);
                    queue.add(currNeighborDto);
                    if (currNeighborKey == id_end) {
                        return path;
                    }
                }
            }

        }

        return path;
    }

    /**
     * @return
     * @throws IllegalAccessException
     */
    public Ride getRide() throws IllegalAccessException {
        if (ride == null)
            throw new IllegalAccessException("Ride is null at this moment.");
        return ride;
    }
}
