package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Test class for GenreSet.
 *
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class GenreSetTest extends student.TestCase {

    /**
     * Private field to hold a GenreSet object for testing.
     */
    private GenreSet genre;

    /**
     * Initializes the GenreSet object for testing.
     */
    public void setUp() {
        genre = new GenreSet(20, 40, 20);
    }


    /**
     * Tests all the getter methods for genre compositions.
     */
    public void testGetter() {
        assertEquals(genre.getPop(), 20);
        assertEquals(genre.getRock(), 40);
        assertEquals(genre.getCountry(), 20);
    }


    /**
     * Tests the isWithinRange() method to check if the genre set is within
     * specified composition limits.
     */
    public void testIsWithinRange() {
        GenreSet other1 = new GenreSet(20, 30, 20);
        GenreSet other2 = new GenreSet(20, 50, 20);
        assertTrue(genre.isWithinRange(other1, other2));
        assertFalse(genre.isWithinRange(other2, other1));
        assertFalse(other2.isWithinRange(other1, genre));
    }


    /**
     * Tests the equals() method to compare GenreSet objects.
     */
    public void testEquals() {
        assertTrue(genre.equals(genre));
        assertFalse(genre.equals(null));
        assertFalse(genre.equals((Object)1));

        GenreSet other1 = new GenreSet(20, 30, 20);
        GenreSet other2 = new GenreSet(20, 40, 20);
        assertFalse(genre.equals(other1));
        assertTrue(genre.equals(other2));
    }


    /**
     * Tests the compareTo() method to compare GenreSet objects based on their
     * compositions.
     */
    public void testCompareTo() {
        GenreSet other1 = new GenreSet(20, 30, 20);
        assertTrue(genre.compareTo(other1) > 0);
    }


    /**
     * Tests the toString() method to get a string representation of the
     * GenreSet object.
     */
    public void testToString() {
        String s = "Pop:20 Rock:40 Country:20";
        assertEquals(genre.toString(), s);
    }
}
