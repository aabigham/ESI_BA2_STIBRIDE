package atl.stibride.model;

import atl.stibride.jdbc.dto.StationDto;

import java.util.List;
import java.util.Objects;

/**
 * This class represents a stib ride.
 */
public class Ride {

    /* The origin metro station */
    private final StationDto origin;
    /* The destination metro station */
    private final StationDto destination;
    /* The path between the two station */
    private final List<StationDto> path;

    /**
     * Constructor of the rise class.
     *
     * @param origin      the origin station.
     * @param destination the destination station.
     * @param path        the  path between the two station
     */
    public Ride(StationDto origin, StationDto destination, List<StationDto> path) {
        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    /**
     * Gets the origin station.
     *
     * @return the origin station.
     */
    public StationDto getOrigin() {
        return origin;
    }

    /**
     * Gets the destination station.
     *
     * @return the destination station.
     */
    public StationDto getDestination() {
        return destination;
    }

    /**
     * Gets the path between the two stations.
     *
     * @return the path between the two stations.
     */
    public List<StationDto> getPath() {
        return path;
    }

    /**
     * Gets the size of the ride path.
     *
     * @return the size of the ride path.
     */
    public int getPathSize() {
        return path.size();
    }

    /**
     * A String representation of the ride.
     *
     * @return a String representation of the ride.
     */
    @Override
    public String toString() {
        return origin + " => " + destination;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride1 = (Ride) o;
        return origin.equals(ride1.origin) && destination.equals(ride1.destination) && path.equals(ride1.path);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables
     * such as those provided by java.util.HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, path);
    }
}
