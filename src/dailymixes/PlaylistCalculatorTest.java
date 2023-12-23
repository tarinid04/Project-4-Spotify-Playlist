/**
 * This class represents a test suite for the PlaylistCalculator class.
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
 * Test class for the PlaylistCalculator class.
 *
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
public class PlaylistCalculatorTest extends TestCase {

    /**
     * The global PlaylistCalculator instance used in all tests.
     */
    private PlaylistCalculator calculator;

    /**
     * Sets up the initial test environment.
     */
    public void setUp() {
        Song song1 = new Song("Hotel California", 23, 47, 1, "Daily Mix 2");
        Song song2 = new Song("Billie Jea", 47, 34, 5, "Daily Mix 2");
        Song song3 = new Song("Dancing Queen", 62, 27, 8, "Daily Mix 1");
        ArrayQueue<Song> queue = new ArrayQueue<Song>();
        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);

        Playlist[] playlists = new Playlist[2];
        playlists[0] = new Playlist("Daily Mix 1", 50, 20, 0, 99, 49, 10, 10);
        playlists[1] = new Playlist("Daily Mix 2", 22, 58, 2, 43, 95, 6, 8);

        calculator = new PlaylistCalculator(queue, playlists);
    }


    /**
     * Test the exception thrown by the constructor.
     */
    public void testConstuctorException() {
        Exception e = null;
        try {
            calculator = new PlaylistCalculator(null, null);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }


    /**
     * Test the getQueue method.
     */
    public void testGetQueue() {
        Song song1 = new Song("Hotel California", 23, 47, 1, "Daily Mix 2");
        Song song2 = new Song("Billie Jea", 47, 34, 5, "Daily Mix 2");
        Song song3 = new Song("Dancing Queen", 62, 27, 8, "Daily Mix 1");
        ArrayQueue<Song> queue = new ArrayQueue<Song>();
        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);
        assertTrue(calculator.getQueue().equals(queue));
    }


    /**
     * Test the getPlaylistIndex() method.
     */
    public void testGetPlaylistIndex() {
        assertEquals(calculator.getPlaylistIndex("Daily Mix 2"), 1);
        assertEquals(calculator.getPlaylistIndex("Daily Mix"), -1);
    }


    /**
     * Test the reject method.
     */
    public void testReject() {
        assertEquals(calculator.getQueue().getSize(), 3);
        calculator.reject();
        assertEquals(calculator.getQueue().getSize(), 2);
    }


    /**
     * Test the getPlaylistWithMostRoom method.
     */
    public void testGetPlaylistWithMostRoom() {
        Song song1 = new Song("A", 25, 59, 4, "");
        assertTrue(calculator.getPlaylistForSong(song1).equals(calculator
            .getPlaylists()[1]));
        Song song2 = new Song("A", 55, 59, 4, "");
        assertEquals(calculator.getPlaylistForSong(song2), null);
    }


    /**
     * Test the getPlaylistForSong method.
     */
    public void testGetPlaylistForSong() {
        assertEquals(calculator.getPlaylistForSong(null), null);
        Song song1 = new Song("A", 25, 59, 4, "Daily Mix 2");
        assertEquals(calculator.getPlaylistForSong(song1), calculator
            .getPlaylists()[1]);
        Song song2 = new Song("A", 21, 59, 9, "Daily Mix 2");
        assertEquals(calculator.getPlaylistForSong(song2), null);
    }


    /**
     * Test the addSongToPlay method.
     */
    public void testAddSongToPlay() {
        assertFalse(calculator.addSongToPlaylist());
        calculator.getQueue().dequeue();
        assertFalse(calculator.addSongToPlaylist());
        assertFalse(calculator.addSongToPlaylist());
        assertFalse(calculator.addSongToPlaylist());
    }
}
