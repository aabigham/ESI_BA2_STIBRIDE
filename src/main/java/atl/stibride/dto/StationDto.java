package atl.stibride.dto;

import java.util.Objects;

public class StationDto extends Dto<Integer> {

    private final int id;
    private final String name;
    // TODO voisin ?

    public StationDto(Integer key, int id, String name) {
        super(key);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StationDto that = (StationDto) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
