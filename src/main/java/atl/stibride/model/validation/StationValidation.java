package atl.stibride.model.validation;

import atl.stibride.jdbc.dto.StationDto;

/**
 * This class allows validation between to stations objects or id.
 * Two stations cannot be the same when computing a ride.
 */
public class StationValidation {

    /**
     * Validate two stations objects. They cannot be similar.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @throws IllegalArgumentException if the two stations are the same.
     */
    public static void validateStations(StationDto origin, StationDto destination)
            throws IllegalArgumentException {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }

    /**
     * Validate two stations id. They cannot be similar.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @throws IllegalArgumentException if the two stations are the same.
     */
    public static void validateStations(Integer origin, Integer destination)
            throws IllegalArgumentException {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Stations cannot be the same.");
        }
    }
}
