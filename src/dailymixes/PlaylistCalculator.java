package dailymixes;

import java.util.Arrays;
import list.AList;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Calculates suitable playlists for songs and manages song assignments to
 * playlists.
 *
 * This class helps in determining the appropriate playlist for songs and
 * facilitates
 * the assignment of songs to playlists based on genre composition requirements.
 *
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
public class PlaylistCalculator {

    /**
     * Stores all playlists.
     */
    private Playlist[] playlists;

    /**
     * Number of playlists allowed.
     */
    public static final int NUM_PLAYLISTS = 3;

    /**
     * Minimum percentage of a song composition.
     */
    public static final int MIN_PERCENT = 0;

    /**
     * Maximum percentage of a song composition.
     */
    public static final int MAX_PERCENT = 100;

    /**
     * List of all rejected songs.
     */
    private AList<Song> rejectedTracks;

    /**
     * ArrayQueue to store songs in the queue.
     */
    private ArrayQueue<Song> songQueue;

    /**
     * Constructor to initialize the PlaylistCalculator.
     *
     * @param queue
     *            ArrayQueue of Song representing songs in the queue.
     * @param playlistArr
     *            Array of playlists available.
     * @throws IllegalArgumentException
     *             if the queue is null.
     */
    public PlaylistCalculator(ArrayQueue<Song> queue, Playlist[] playlistArr) {
        if (queue == null) {
            throw new IllegalArgumentException("Queue cannot be null.");
        }
        songQueue = queue;
        playlists = playlistArr;
        rejectedTracks = new AList<Song>();
    }


    /**
     * Get a suitable playlist for the given song.
     *
     * @param nextSong
     *            The song for which a suitable playlist is needed.
     * @return Playlist object where the song can be added, or null if no
     *         suitable playlist is found.
     */
    public Playlist getPlaylistForSong(Song nextSong) {
        if (nextSong == null) {
            return null;
        }
        String suggestedPlaylist = nextSong.getPlaylistName();
        if (!suggestedPlaylist.equals("")) {
            for (Playlist playlist : playlists) {
                if (playlist.getName().equals(suggestedPlaylist)) {
                    if (playlist.isQualified(nextSong)) {
                        return playlist;
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        return getPlaylistWithMostRoom(nextSong);
    }


    /**
     * Get a playlist with the most available space and songs most qualified to
     * be added.
     *
     * @param newSong
     *            The song to be checked for qualification.
     * @return Playlist most suitable for adding the given song, or null if no
     *         suitable playlist is found.
     */
    private Playlist getPlaylistWithMostRoom(Song newSong) {
        Playlist[] sortPlaylists = playlists.clone();
        Arrays.parallelSort(sortPlaylists);
        for (int i = sortPlaylists.length - 1; i >= 0; i--) {
            if (sortPlaylists[i].isQualified(newSong)) {
                return sortPlaylists[i];
            }
        }
        return null;
    }


    /**
     * Adds the first song from songQueue to a suitable playlist.
     *
     * @return true if the song is successfully added to a playlist, else false.
     */
    public boolean addSongToPlaylist() {
        if (!songQueue.isEmpty()) {
            Playlist requiredPlaylist = getPlaylistForSong(songQueue
                .getFront());
            if (requiredPlaylist != null) {
                Song toBeAddedSong = songQueue.dequeue();
                requiredPlaylist.addSong(toBeAddedSong);
                return true;
            }
        }
        return false;
    }


    /**
     * Adds the rejected song from the queue to the rejectedTracks list.
     */
    public void reject() {
        rejectedTracks.add(songQueue.dequeue());
    }


    /**
     * Search for a playlist by its name.
     *
     * @param name
     *            The name of the playlist to search for.
     * @return Index of the playlist if found, or -1 if not found.
     */
    public int getPlaylistIndex(String name) {
        for (int i = 0; i < playlists.length; i++) {
            if (playlists[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Get the queue of songs.
     *
     * @return ArrayQueue<Song> representing the queue of songs.
     */
    public ArrayQueue<Song> getQueue() {
        return this.songQueue;
    }


    /**
     * Get all the playlists.
     *
     * @return Array of playlists.
     */
    public Playlist[] getPlaylists() {
        return this.playlists;
    }
}
