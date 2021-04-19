package atl.stibride.model;

import atl.stibride.config.ConfigManager;
import atl.stibride.dto.StationDto;
import atl.stibride.repository.RepositoryException;
import atl.stibride.repository.StationRepository;
import atl.stibride.repository.StopRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private StationRepository stationRepo;
    private StopRepository stopRepository;

    public Model() {
        try {
            ConfigManager.getInstance().load();
            this.stationRepo = new StationRepository();
            this.stopRepository = new StopRepository();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<StationDto> getStations() throws RepositoryException {
        return stationRepo.getAll();
    }

    /**
     * Computes the best path to take between the two stations.
     *
     * @param start the starting station.
     * @param end   the end station.
     * @return a list of stations describing the best path to take between the two stations.
     */
    public static List<StationDto> getRide(StationDto start, StationDto end) {
        // TODO
        return new ArrayList<>();
    }
}
