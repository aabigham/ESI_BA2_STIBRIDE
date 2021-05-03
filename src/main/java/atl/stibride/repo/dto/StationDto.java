package atl.stibride.repo.dto;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationDto extends Dto<Integer> {

    private final String name;
    private final List<Pair<Integer, Integer>> lines; // Ligne + son ordre dans la ligne
    private final List<Integer> neighbors;

    public StationDto(int id, String name) {
        super(id);
        this.name = name;
        this.lines = new ArrayList<>();
        this.neighbors = new ArrayList<>();
    }

    public void addLine(int line, int order) {
        lines.add(new Pair<>(line, order));
    }

    public void addNeighbor(Integer id_station) {
        neighbors.add(id_station);
    }

    public String getName() {
        return name;
    }

    public List<Pair<Integer, Integer>> getLinesAsList() {
        return lines;
    }

    public String getLines() {
        StringBuilder ret = new StringBuilder("[");
        int length = lines.size();
        for (int i = 0; i < length; i++) {
            ret.append(lines.get(i).getKey());
            if (i < length - 1)
                ret.append(", ");
        }
        ret.append("]");
        return ret.toString();
    }

    public List<Integer> getNeighbors() {
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
