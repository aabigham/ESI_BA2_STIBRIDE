package atl.stibride.model;

import atl.stibride.repo.dto.StationDto;

public class StationValidation {

    public static void validateStations(StationDto origin, StationDto destination)
            throws IllegalArgumentException {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }

    public static void validateStations(Integer origin, Integer destination)
            throws IllegalArgumentException {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }
}
