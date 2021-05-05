package atl.stibride.model;

import atl.stibride.repo.dto.StationDto;

public class StationValidation {

    public static void validateStations(StationDto first, StationDto second)
            throws IllegalArgumentException {
        if (first.equals(second)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }

    public static void validateStations(Integer first, Integer second)
            throws IllegalArgumentException {
        if (first.equals(second)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }
}
