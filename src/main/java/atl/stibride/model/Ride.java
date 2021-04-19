package atl.stibride.model;

import atl.stibride.dto.StationDto;

import java.util.List;
import java.util.Objects;

public class Ride {

    private final StationDto origin;
    private final StationDto destination;
    private final List<StationDto> path;

    public Ride(StationDto origin, StationDto destination, List<StationDto> path) {
        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    public StationDto getOrigin() {
        return origin;
    }

    public StationDto getDestination() {
        return destination;
    }

    public List<StationDto> getPath() {
        return path;
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
        return origin.equals(ride1.origin) && destination.equals(ride1.destination) && path.equals(ride1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, path);
    }
}
