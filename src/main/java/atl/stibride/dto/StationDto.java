package atl.stibride.dto;

import java.util.List;
import java.util.Objects;

public class StationDto extends Dto<Integer> {

    private final String name;
    //private List<StationDto> neighbors;

    public StationDto(int id, String name) {
        super(id);
        this.name = name;
        //this.neighbors = new ArrayList<>();
    }

    /*public void addNeighbor(StationDto dto) {
        neighbors.add(dto);
    }*/

    public String getName() {
        return name;
    }

    public List<StationDto> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StationDto that = (StationDto) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

}
