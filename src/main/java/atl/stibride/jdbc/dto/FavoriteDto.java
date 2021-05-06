package atl.stibride.jdbc.dto;

import java.util.Objects;

/**
 * Data transfer object for a favorite row in the FAVORITES table
 * inside the database.
 * <p>
 * This class inherits the DtoPair class.
 */
public class FavoriteDto extends DtoPair<Integer> {

    /* The name of the favorite */
    private String name;

    /**
     * Constructor of FavoriteDto.
     *
     * @param firstKey  the first key of the favorite.
     * @param secondKey the second key of the favorite.
     * @param name      the name of the favorite.
     */
    public FavoriteDto(Integer firstKey, Integer secondKey, String name) {
        super(firstKey, secondKey);
        this.name = name;
    }

    /**
     * Gets the name of the favorite.
     *
     * @return the name of the favorite.
     */
    public String getName() {
        return name;
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
        FavoriteDto that = (FavoriteDto) o;
        return name.equals(that.name);
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
        return Objects.hash(super.hashCode(), name);
    }
}
