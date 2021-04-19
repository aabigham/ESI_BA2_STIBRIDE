package atl.stibride.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationDto extends Dto<Integer> {

    private final String name;
    private List<Integer> lines;
    private List<StationDto> neighbors;

    public StationDto(int id, String name) {
        super(id);
        this.name = name;
        this.lines = new ArrayList<>();
        this.neighbors = new ArrayList<>();
    }

    public void addLine(int line) {
        lines.add(line);
    }

    public void addNeighbor(StationDto dto) {
        neighbors.add(dto);
    }

    public String getName() {
        return name;
    }

    public String getLines() {
        return lines.toString();
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
        return name.equals(that.name) && lines.equals(that.lines) && neighbors.equals(that.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, lines, neighbors);
    }
}
