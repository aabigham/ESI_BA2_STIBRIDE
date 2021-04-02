package atl.stibride.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StopDto extends Dto<Integer> {

    private final int id_station;
    private final int id_order;
    private final List<StopDto> neighbors = new ArrayList<>();

    public StopDto(int id_line, int id_station, int id_order) {
        super(id_line);
        this.id_station = id_station;
        this.id_order = id_order;
    }

    public void addNeighbor(StopDto neighbor) {
        neighbors.add(neighbor);
    }

    public void removeNeighbor(StopDto neighbor) {
        neighbors.remove(neighbor);
    }

    public int getId_station() {
        return id_station;
    }

    public int getId_order() {
        return id_order;
    }

    public List<StopDto> getNeighbors() {
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StopDto stopDto = (StopDto) o;
        return id_station == stopDto.id_station
                && id_order == stopDto.id_order
                && neighbors.equals(stopDto.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_station, id_order, neighbors);
    }
}
