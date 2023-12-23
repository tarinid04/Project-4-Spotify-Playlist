package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Test class for the Song class.
 * 
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class SongTest extends student.TestCase {

    private Song song;

    /**
     * Set up the test environment by creating an instance of the Song class.
     */

    public void setUp() {
        song = new Song("Heartless", 20, 40, 20, "p2");
    }


    /**
     * Test getter methods of the Song class.
     */
    public void testGetters() {
        assertEquals("Heartless", song.getName());
        assertEquals("p2", song.getPlaylistName());
        GenreSet expectedGenreSet = new GenreSet(20, 40, 20);
        assertTrue(expectedGenreSet.equals(song.getGenreSet()));
    }


    /**
     * Test the toString method of the Song class.
     */
    public void testToString() {
        String expectedWithSuggestedPlaylist =
            "Heartless Pop:20 Rock:40 Country:20 Suggested: p2";
        assertEquals(expectedWithSuggestedPlaylist, song.toString());

        Song songWithoutPlaylist = new Song("Heartless", 20, 40, 20, "");
        String expectedWithoutSuggestedPlaylist =
            "No-Playlist Heartless Pop:20 Rock:40 Country:20";
        assertEquals(expectedWithoutSuggestedPlaylist, songWithoutPlaylist
            .toString());
    }


    /**
     * Test the equals method of the Song class.
     */
    public void testEquals() {
        assertFalse(song.equals(null));
        assertTrue(song.equals(song));
        assertFalse(song.equals((Object)1));

        Song songWithSameAttributes = new Song("Heartless", 20, 40, 20, "p2");
        Song songWithDifferentName = new Song("Heartfull", 20, 40, 20, "p2");
        Song songWithDifferentPop = new Song("Heartless", 30, 40, 20, "p2");
        Song songWithDifferentPlaylist = new Song("Heartless", 20, 40, 20,
            "p3");

        assertTrue(song.equals(songWithSameAttributes));
        assertFalse(song.equals(songWithDifferentName));
        assertFalse(song.equals(songWithDifferentPop));
        assertFalse(song.equals(songWithDifferentPlaylist));
    }
}
