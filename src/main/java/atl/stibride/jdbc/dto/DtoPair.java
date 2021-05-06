package atl.stibride.jdbc.dto;

import java.util.Objects;

/**
 * Generic class to build a data tranfer objects with two keys.
 *
 * @param <K> key type of the data.
 */
public class DtoPair<K> {

    /**
     * The first key.
     */
    protected K firstKey;

    /**
     * The second key.
     */
    protected K secondKey;

    /**
     * Constructor of DtoPair.
     *
     * @param firstKey  the first key of the dto.
     * @param secondKey the second key of the dto.
     */
    public DtoPair(K firstKey, K secondKey) {
        if (firstKey == null || secondKey == null) {
            throw new IllegalArgumentException("Invalid keys");
        }
        this.firstKey = firstKey;
        this.secondKey = secondKey;
    }

    /**
     * Gets the first key of the object.
     *
     * @return the first key of the object.
     */
    public K getFirstKey() {
        return firstKey;
    }

    /**
     * Gets the second key of the object.
     *
     * @return the second key of the object.
     */
    public K getSecondKey() {
        return secondKey;
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
        DtoPair<?> dtoPair = (DtoPair<?>) o;
        return firstKey.equals(dtoPair.firstKey) && secondKey.equals(dtoPair.secondKey);
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
        return Objects.hash(firstKey, secondKey);
    }
}
