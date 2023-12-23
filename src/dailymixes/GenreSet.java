package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Represents a set of music genre compositions.
 *
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class GenreSet implements Comparable<GenreSet> {

    /**
     * Private field for rock composition.
     */
    private int rock;

    /**
     * Private field for pop composition.
     */
    private int pop;

    /**
     * Private field for country composition.
     */
    private int country;

    /**
     * Constructs a GenreSet object with the specified genre compositions.
     *
     * @param popP
     *            Pop composition
     * @param rockP
     *            Rock composition
     * @param countryP
     *            Country composition
     */
    public GenreSet(int popP, int rockP, int countryP) {
        rock = rockP;
        pop = popP;
        country = countryP;
    }


    /**
     * Gets the rock composition.
     *
     * @return The value of the private field rock.
     */
    public int getRock() {
        return rock;
    }


    /**
     * Gets the pop composition.
     *
     * @return The value of the private field pop.
     */
    public int getPop() {
        return pop;
    }


    /**
     * Gets the country composition.
     *
     * @return The value of the private field country.
     */
    public int getCountry() {
        return country;
    }


    /**
     * Compares this GenreSet object to another GenreSet object.
     *
     * @param other
     *            The other GenreSet to compare.
     * @return True if all compositions of this class are less than or equal
     *         to the other class's compositions.
     */
    private boolean isLessThanOrEqualTo(GenreSet other) {
        return (this.rock <= other.rock && this.pop <= other.pop
            && this.country <= other.country);
    }


    /**
     * Checks if this genre is within the specified minimum and maximum genre
     * compositions.
     *
     * @param minGenreSet
     *            The GenreSet representing the minimum composition limits.
     * @param maxGenreSet
     *            The GenreSet representing the maximum composition limits.
     * @return True if all attributes in the genre set are greater than or equal
     *         to
     *         the attributes in the minimum genre set and less than or equal to
     *         the attributes in the maximum genre set.
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet) {
        return this.isLessThanOrEqualTo(maxGenreSet) && minGenreSet
            .isLessThanOrEqualTo(this);
    }


    /**
     * Overrides the equals method to compare this GenreSet with another object.
     *
     * @param obj
     *            The object to compare.
     * @return True if all attributes of this class are equal to obj's class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            GenreSet other = (GenreSet)obj;
            return isWithinRange(other, other);
        }
        return false;
    }


    /**
     * Overrides the compareTo method to compare this GenreSet with another
     * GenreSet.
     *
     * @param obj
     *            The GenreSet object to compare.
     * @return A positive value if the sum of this class's attributes is greater
     *         than
     *         obj's attributes sum, 0 if they are equal, a negative value if
     *         they are less than
     *         obj's attributes.
     */
    @Override
    public int compareTo(GenreSet obj) {
        int thisGenreSum = this.getCountry() + this.getPop() + this.getRock();
        int otherGenreSum = obj.getCountry() + obj.getPop() + obj.getRock();
        return thisGenreSum - otherGenreSum;
    }


    /**
     * Overrides the toString method to provide a string representation of the
     * class.
     *
     * @return A string value representing the genre compositions.
     */
    @Override
    public String toString() {
        return "Pop:" + this.pop + " Rock:" + this.rock + " Country:"
            + this.country;
    }
}
