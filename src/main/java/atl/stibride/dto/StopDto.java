package atl.stibride.dto;

import java.util.Objects;

public class StopDto extends Dto<Integer> {

    private final int id_line;
    private final int id_station;
    private final int id_order;
    // TODO voisin ?

    public StopDto(Integer key, int id_line, int id_station, int id_order) {
        super(key);
        this.id_line = id_line;
        this.id_station = id_station;
        this.id_order = id_order;
    }

    public int getId_line() {
        return id_line;
    }

    public int getId_station() {
        return id_station;
    }

    public int getId_order() {
        return id_order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StopDto stopDto = (StopDto) o;
        return id_line == stopDto.id_line && id_station == stopDto.id_station && id_order == stopDto.id_order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_line, id_station, id_order);
    }
}
