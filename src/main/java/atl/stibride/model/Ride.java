package atl.stibride.model;

import atl.stibride.dto.StationDto;

import java.util.List;
import java.util.Objects;

public class Ride {

    private final StationDto origin;
    private final StationDto destination;
    private final List<StationDto> ride;
    
    public Ride(StationDto origin, StationDto destination, List<StationDto> ride) {
        this.origin = origin;
        this.destination = destination;
        this.ride = ride;
    }

    public StationDto getOrigin() {
        return origin;
    }

    public StationDto getDestination() {
        return destination;
    }

    public List<StationDto> getRide() {
        return ride;
    }

    @Override
    public String toString() {
        return origin + " => " + destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride1 = (Ride) o;
        return origin.equals(ride1.origin) && destination.equals(ride1.destination) && ride.equals(ride1.ride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, ride);
    }
}
