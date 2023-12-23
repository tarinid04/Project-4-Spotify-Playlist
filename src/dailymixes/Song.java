package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Represents a song with its name, genre composition, and suggested playlist.
 * This class is used to store information about songs and their attributes.
 *
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class Song {

    /**
     * Private field for the name of the song.
     */
    private String name;

    /**
     * Private field for the suggested playlist for the song.
     */
    private String suggestedPlaylist;

    /**
     * Private field for the song's composition represented as a GenreSet.
     */
    private GenreSet genreSet;

    /**
     * Constructs a Song object with the given parameters.
     *
     * @param nameS
     *            The name of the song.
     * @param popP
     *            The pop percentage of the song.
     * @param rockP
     *            The rock percentage of the song.
     * @param countryP
     *            The country percentage of the song.
     * @param suggested
     *            The suggested playlist for the song.
     */
    public Song(
        String nameS,
        int popP,
        int rockP,
        int countryP,
        String suggested) {
        name = nameS;
        suggestedPlaylist = suggested;
        genreSet = new GenreSet(popP, rockP, countryP);
    }


    /**
     * Gets the name of the song.
     *
     * @return The name of the song.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Gets the genre composition of the song as a GenreSet object.
     *
     * @return GenreSet representing the composition of the song.
     */
    public GenreSet getGenreSet() {
        return this.genreSet;
    }


    /**
     * Gets the suggested playlist for the song.
     *
     * @return The suggested playlist for the song.
     */
    public String getPlaylistName() {
        return this.suggestedPlaylist;
    }


    /**
     * Overrides the toString method to provide a string representation of the
     * Song object.
     *
     * @return A string representation of the Song, including its name, genre
     *         composition,
     *         and suggested playlist if available.
     */
    @Override
    public String toString() {
        String s = this.name + " " + genreSet.toString();
        if (this.suggestedPlaylist.length() > 0) {
            return s + " Suggested: " + this.suggestedPlaylist;
        }
        return "No-Playlist " + s;
    }


    /**
     * Overrides the equals method to compare two Song objects for equality.
     *
     * @param obj
     *            The other object to compare.
     * @return True if the other object has the same attributes as this Song
     *         object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Song other = (Song)obj;
        return this.name.equals(other.getName()) && this.genreSet.equals(other
            .getGenreSet()) && this.suggestedPlaylist.equals(other
                .getPlaylistName());
    }
}
