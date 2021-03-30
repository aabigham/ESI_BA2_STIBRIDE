package atl.stibride.dto;

import java.util.Objects;

public class LineDto extends Dto<Integer> {

    private final int id;

    public LineDto(Integer key, int id) {
        super(key);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LineDto lineDto = (LineDto) o;
        return id == lineDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
