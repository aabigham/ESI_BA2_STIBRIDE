package atl.stibride.repo.dto;

import java.util.Objects;

public class FavoriteDto extends DtoPair<Integer> {

    // start station key is the super key
    private String name;

    public FavoriteDto(Integer firstKey, Integer secondKey, String name) {
        super(firstKey, secondKey);
        this.name = name;
    }

    public String getName() {
        return name;
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
        FavoriteDto that = (FavoriteDto) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
