package atl.stibride.repo.dto;

import java.util.Objects;

public class DtoPair<K> {

    protected K firstKey;

    protected K secondKey;

    public DtoPair(K firstKey, K secondKey) {
        if (firstKey == null || secondKey == null) {
            throw new IllegalArgumentException("Invalid keys");
        }
        this.firstKey = firstKey;
        this.secondKey = secondKey;
    }

    public K getFirstKey() {
        return firstKey;
    }

    public K getSecondKey() {
        return secondKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DtoPair<?> dtoPair = (DtoPair<?>) o;
        return firstKey.equals(dtoPair.firstKey) && secondKey.equals(dtoPair.secondKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstKey, secondKey);
    }
}
