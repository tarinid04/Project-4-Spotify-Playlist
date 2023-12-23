package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Represents a playlist with specific genre composition requirements and a
 * collection of songs.
 *
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
public class Playlist implements Comparable<Playlist> {

    /**
     * Holds the minimum required values of a genreSet to be in the playlist.
     */
    private GenreSet minGenreSet;

    /**
     * Holds the maximum required values of a genreSet to be in the playlist.
     */
    private GenreSet maxGenreSet;

    /**
     * Array containing all the songs in the playlist.
     */
    private Song[] songs;

    /**
     * Capacity of songs array.
     */
    private int capacity;

    /**
     * Number of songs in the songs array.
     */
    private int numberOfSongs;

    /**
     * Name of the playlist.
     */
    private String name;

    /**
     * Constructor to create a Playlist with specific genre composition
     * requirements.
     *
     * @param playlistName
     *            Name of the playlist
     * @param minPop
     *            Minimum pop composition percentage
     * @param minRock
     *            Minimum rock composition percentage
     * @param minCountry
     *            Minimum country composition percentage
     * @param maxPop
     *            Maximum pop composition percentage
     * @param maxRock
     *            Maximum rock composition percentage
     * @param maxCountry
     *            Maximum country composition percentage
     * @param playlistCap
     *            Capacity of the playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap) {
        name = playlistName;
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        capacity = playlistCap;
        numberOfSongs = 0;
        songs = new Song[capacity];
    }


    /**
     * Gets the minimum GenreSet representing the minimum composition
     * requirements.
     *
     * @return GenreSet representing the minimum values
     */
    public GenreSet getMinGenreSet() {
        return this.minGenreSet;
    }


    /**
     * Gets the maximum GenreSet representing the maximum composition
     * requirements.
     *
     * @return GenreSet representing the maximum values
     */
    public GenreSet getMaxGenreSet() {
        return this.maxGenreSet;
    }


    /**
     * Sets the name of the playlist.
     *
     * @param newName
     *            The new name of the playlist
     */
    public void setName(String newName) {
        this.name = newName;
    }


    /**
     * Gets the number of available spaces left in the playlist.
     *
     * @return The capacity of the playlist minus the number of songs
     */
    public int getSpacesLeft() {
        return capacity - numberOfSongs;
    }


    /**
     * Gets the number of songs in the playlist.
     *
     * @return The number of songs in the playlist
     */
    public int getNumberOfSongs() {
        return this.numberOfSongs;
    }


    /**
     * Gets an array of songs in the playlist.
     *
     * @return An array of Song objects in the playlist
     */
    public Song[] getSongs() {
        return this.songs;
    }


    /**
     * Gets the capacity of the playlist.
     *
     * @return The size of the song array representing the playlist's capacity
     */
    public int getCapacity() {
        return this.capacity;
    }


    /**
     * Gets the name of the playlist.
     *
     * @return The name of the playlist
     */
    public String getName() {
        return this.name;
    }


    /**
     * Checks if the playlist is full, i.e., if the number of songs has reached
     * the playlist's capacity.
     *
     * @return True if the playlist's capacity equals the number of songs,
     *         otherwise false.
     */
    public boolean isFull() {
        return capacity == numberOfSongs;
    }


    /**
     * Adds a song to the playlist after checking if it qualifies based on genre
     * composition.
     *
     * @param newSong
     *            The Song to add to the playlist
     * @return True if the song is added successfully, otherwise false
     */
    public boolean addSong(Song newSong) {
        if (this.isQualified(newSong)) {
            songs[this.numberOfSongs++] = newSong;
            return true;
        }
        return false;
    }


    /**
     * Checks if a song qualifies to be in the playlist based on genre
     * composition requirements.
     *
     * @param song
     *            The Song to check for qualification
     * @return True if the song is qualified, otherwise false
     */
    public boolean isQualified(Song song) {
        return song.getGenreSet().isWithinRange(minGenreSet, maxGenreSet)
            && !this.isFull();
    }


    /**
     * Overrides the toString method to provide a string representation of the
     * Playlist object.
     *
     * @return A string representation of the Playlist, including name, number
     *         of songs,
     *         capacity, and genre composition requirements.
     */
    @Override
    public String toString() {
        String s = "Playlist: " + name + ", # of songs: ";
        s += this.numberOfSongs + " (cap: " + capacity;
        s += "), Requires: ";
        s += "Pop:" + minGenreSet.getPop() + "%-" + maxGenreSet.getPop()
            + "%, ";
        s += "Rock:" + minGenreSet.getRock() + "%-" + maxGenreSet.getRock()
            + "%, ";
        s += "Country:" + minGenreSet.getCountry() + "%-" + maxGenreSet
            .getCountry() + "%";

        return s;
    }


    /**
     * Overrides the equals method to compare two Playlist objects based on
     * their attributes.
     *
     * @param obj
     *            The object to compare
     * @return True if both objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            Playlist other = (Playlist)obj;
            if (!this.toString().equals(other.toString())) {
                return false;
            }
            for (int i = 0; i < this.numberOfSongs; i++) {
                if (!this.songs[i].equals(other.songs[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Implements the compareTo method from the Comparable interface to compare
     * Playlist objects.
     *
     * @param obj
     *            The object to compare
     * @return A positive value if this object is greater, a negative value if
     *         the other object
     *         is greater, and 0 if they are equal
     */
    @Override
    public int compareTo(Playlist obj) {
        int value = this.capacity - obj.capacity;
        if (value != 0) {
            return value;
        }
        value = this.getSpacesLeft() - obj.getSpacesLeft();
        if (value != 0) {
            return value;
        }
        value = this.minGenreSet.compareTo(obj.minGenreSet);
        if (value != 0) {
            return value;
        }
        value = this.maxGenreSet.compareTo(obj.maxGenreSet);
        if (value != 0) {
            return value;
        }
        return this.name.compareTo(obj.name);
    }
}
