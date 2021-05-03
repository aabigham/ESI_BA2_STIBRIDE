package atl.stibride.dto;

import java.util.Objects;

public class FavoriteDto extends Dto<Integer> {

    // start station key is the super key
    private int endStationKey;
    private String nameStart;
    private String nameEnd;

    public FavoriteDto(Integer key, int endStationKey, String nameStart, String nameEnd) {
        super(key);
        this.endStationKey = endStationKey;
        this.nameStart = nameStart;
        this.nameEnd = nameEnd;
    }

    public int getEndStationKey() {
        return endStationKey;
    }

    public String getNameStart() {
        return nameStart;
    }

    public String getNameEnd() {
        return nameEnd;
    }

    @Override
    public String toString() {
        return nameStart + " => " + nameEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FavoriteDto that = (FavoriteDto) o;
        return endStationKey == that.endStationKey && nameStart.equals(that.nameStart) && nameEnd.equals(that.nameEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), endStationKey, nameStart, nameEnd);
    }
}
