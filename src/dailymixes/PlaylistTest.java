/**
 * This class represents a test suite for the Playlist class.
 */
package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

import student.TestCase;

/**
 * Test class for the Playlist class.
 *
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
public class PlaylistTest extends TestCase {

    /**
     * The Playlist object used in every test.
     */
    private Playlist playlist;

    /**
     * Set up the initial test environment.
     */
    public void setUp() {
        playlist = new Playlist("Na", 30, 34, 38, 33, 37, 41, 5);
        Song song1 = new Song("1", 31, 35, 39, "");
        Song song2 = new Song("2", 32, 35, 39, "");
        playlist.addSong(song1);
        playlist.addSong(song2);
    }


    /**
     * Test all the one-liner functions.
     */
    public void testOneLiners() {
        assertEquals(playlist.getSpacesLeft(), 3);
        assertFalse(playlist.isFull());
        Song song3 = new Song("3", 31, 35, 39, "");
        Song song4 = new Song("4", 31, 35, 39, "");
        Song song5 = new Song("5", 31, 35, 39, "");
        playlist.addSong(song5);
        playlist.addSong(song4);
        playlist.addSong(song3);
        assertTrue(playlist.isFull());
    }


    /**
     * Test the addSongs() method.
     */
    public void testAddSongs() {
        Song song3 = new Song("3", 31, 35, 39, "");
        assertTrue(playlist.addSong(song3));
        Song song4 = new Song("3", 29, 35, 39, "");
        assertFalse(playlist.addSong(song4));
        playlist.addSong(song3);
        playlist.addSong(song3);
        playlist.addSong(song3);
        assertFalse(playlist.addSong(song3));
    }


    /**
     * Test the toString() method.
     */
    public void testToString() {
        String s = "Playlist: Na, # of songs: 2 (cap: 5), "
            + "Requires: Pop:30%-33%, Rock:34%-37%, Country:38%-41%";
        assertEquals(playlist.toString(), s);
    }


    /**
     * Test the equals method.
     */
    public void testEquals() {
        assertFalse(playlist.equals(null));
        assertFalse(playlist.equals((Object)1));
        assertTrue(playlist.equals(playlist));
        Playlist playlist1 = new Playlist("NA", 30, 34, 38, 33, 37, 41, 5);
        assertFalse(playlist.equals(playlist1));
        playlist1 = new Playlist("Na", 30, 34, 38, 33, 37, 41, 5);
        assertFalse(playlist.equals(playlist1));

        Song song1 = new Song("1", 31, 35, 39, "");
        Song song2 = new Song("3", 32, 35, 39, "");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        assertFalse(playlist.equals(playlist1));

        playlist1 = new Playlist("Na", 30, 34, 38, 33, 37, 41, 5);
        song1 = new Song("1", 31, 35, 39, "");
        song2 = new Song("2", 32, 35, 39, "");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        assertTrue(playlist.equals(playlist1));
    }


    /**
     * Test the compareTo method.
     */
    public void testCompareTo() {
        Playlist playlist1 = new Playlist("NA", 30, 34, 38, 33, 37, 41, 10);
        assertTrue(playlist.compareTo(playlist1) < 0);

        playlist1 = new Playlist("NA", 30, 34, 38, 33, 37, 41, 5);
        assertTrue(playlist.compareTo(playlist1) < 0);

        playlist1 = new Playlist("NA", 29, 34, 38, 33, 37, 41, 5);
        Song song1 = new Song("1", 31, 35, 39, "");
        Song song2 = new Song("2", 32, 35, 39, "");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        assertTrue(playlist.compareTo(playlist1) > 0);

        playlist1 = new Playlist("NA", 30, 34, 38, 33, 37, 43, 5);
        song1 = new Song("1", 31, 35, 39, "");
        song2 = new Song("2", 32, 35, 39, "");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        assertTrue(playlist.compareTo(playlist1) < 0);

        playlist1 = new Playlist("NA", 30, 34, 38, 33, 37, 41, 5);
        song1 = new Song("1", 31, 35, 39, "");
        song2 = new Song("2", 32, 35, 39, "");
        playlist1.addSong(song1);
        playlist1.addSong(song2);

        assertTrue(playlist.compareTo(playlist1) > 0);
    }
}
