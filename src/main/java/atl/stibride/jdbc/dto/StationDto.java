package atl.stibride.jdbc.dto;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data transfer object for a station row in the STATIONS table
 * inside the database.
 * <p>
 * This class inherits the DtoPair class.
 */
public class StationDto extends Dto<Integer> {

    /* The name of the stations */
    private final String name;
    /* A list of lines in which the stations is, plus the order in the according line */
    private final List<Pair<Integer, Integer>> lines;
    /* A list of the neighboring stations */
    private final List<Integer> neighbors;

    /**
     * Constructor of StationDto.
     *
     * @param id   the id of the station.
     * @param name the name of the station.
     */
    public StationDto(int id, String name) {
        super(id);
        this.name = name;
        this.lines = new ArrayList<>();
        this.neighbors = new ArrayList<>();
    }

    /**
     * Adds a lines in which the stations belongs.
     *
     * @param line  the line in which the stations belongs.
     * @param order the order of the station in this line.
     */
    public void addLine(int line, int order) {
        lines.add(new Pair<>(line, order));
    }

    /**
     * Adds a neighbor stations to this one.
     *
     * @param id_station the id of the neighboring station.
     */
    public void addNeighbor(Integer id_station) {
        neighbors.add(id_station);
    }

    /**
     * Gets the name of the station.
     *
     * @return the name of the station.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of lines in which this station appears,
     * along with its order inside of it.
     *
     * @return the list of lines in which this station appears,
     * along with its order inside of it.
     */
    public List<Pair<Integer, Integer>> getLinesAsList() {
        return lines;
    }

    /**
     * Gets a String representation of the lines (used in the fxml cell property).
     *
     * @return a String representation of the lines.
     */
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

    /**
     * Gets the neighbors of this station.
     *
     * @return the neighbors of this station.
     */
    public List<Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets a String representation of the object.
     *
     * @return a String representation of the object.
     */
    @Override
    public String toString() {
        return name;
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
        if (!super.equals(o)) return false;
        StationDto that = (StationDto) o;
        return name.equals(that.name) && lines.equals(that.lines) && neighbors.equals(that.neighbors);
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
        return Objects.hash(super.hashCode(), name, lines, neighbors);
    }
}
