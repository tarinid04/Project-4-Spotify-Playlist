/**
 * This class is responsible for reading song and playlist data from files and
 * creating
 * corresponding objects.
 */
package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * PlaylistReader class is used for reading song and playlist data from files.
 *
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
public class PlaylistReader {

    ArrayQueue<Song> queue;
    Playlist[] playlists;

    /**
     * Constructs a PlaylistReader with song and playlist filenames, and reads
     * the data.
     *
     * @param songsFileName
     *            The filename of the song data file.
     * @param playlistsFileName
     *            The filename of the playlist data file.
     * @throws ParseException
     *             If there's a parsing error.
     * @throws FileNotFoundException
     *             If the files are not found.
     * @throws DailyMixDataException
     *             If there's an issue with Daily Mix data.
     */
    public PlaylistReader(String songsFileName, String playlistsFileName)
        throws ParseException,
        FileNotFoundException,
        DailyMixDataException {
        queue = this.readQueueFile(songsFileName);
        playlists = this.readPlaylistFile(playlistsFileName);
    }


    /**
     * Checks if the provided numbers are in a valid percentage range.
     *
     * @param num1
     *            The first number to check.
     * @param num2
     *            The second number to check.
     * @param num3
     *            The third number to check.
     * @return True if all numbers are within the valid range; otherwise, false.
     */
    private boolean isInValidPercentRange(int num1, int num2, int num3) {
        int min = PlaylistCalculator.MIN_PERCENT;
        int max = PlaylistCalculator.MAX_PERCENT;
        return (min <= num1 && num1 <= max) && (min <= num2 && max >= num2)
            && (min <= num3 && max >= num3);
    }


    /**
     * Reads playlist data from a file and creates Playlist objects.
     *
     * @param playlistFileName
     *            The filename of the playlist data file.
     * @return An array of Playlist objects.
     * @throws FileNotFoundException
     *             If the file is not found.
     * @throws ParseException
     *             If there's a parsing error.
     * @throws DailyMixDataException
     *             If there's an issue with Daily Mix data.
     */
    private Playlist[] readPlaylistFile(String playlistFileName)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException {
        Playlist[] returnPlaylist =
            new Playlist[PlaylistCalculator.NUM_PLAYLISTS];
        Scanner file = new Scanner(new File(playlistFileName));
        int lineCount = 0;
        while (file.hasNextLine()
            && lineCount < PlaylistCalculator.NUM_PLAYLISTS) {
            String read = file.nextLine();
            Scanner currLine = new Scanner(read).useDelimiter(",\\s*");
            String tokens[] = new String[8];
            int tokenCount = 0;
            while (currLine.hasNext() && tokenCount < 8) {
                tokens[tokenCount++] = currLine.next();
            }
            currLine.close();
            if (tokenCount == 8) {
                if (!this.isInValidPercentRange(Integer.valueOf(tokens[1]),
                    Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3]))) {
                    throw new DailyMixDataException(""); // TODO: Message for
                                                         // error
                }
                if (!this.isInValidPercentRange(Integer.valueOf(tokens[4]),
                    Integer.valueOf(tokens[5]), Integer.valueOf(tokens[6]))) {
                    throw new DailyMixDataException(""); // TODO: Message for
                                                         // error
                }
                Playlist playlist = new Playlist(tokens[0], Integer.valueOf(
                    tokens[1]), Integer.valueOf(tokens[2]), Integer.valueOf(
                        tokens[3]), Integer.valueOf(tokens[4]), Integer.valueOf(
                            tokens[5]), Integer.valueOf(tokens[6]), Integer
                                .valueOf(tokens[7]));
                returnPlaylist[lineCount++] = playlist;
            }
            else {
                throw new ParseException("parse exception", 1);
            }
        }
        file.close();
        return returnPlaylist;
    }


    /**
     * Reads song data from a file and creates Song objects.
     *
     * @param songFileName
     *            The filename of the song data file.
     * @return An ArrayQueue of Song objects.
     * @throws ParseException
     *             If there's a parsing error.
     * @throws FileNotFoundException
     *             If the file is not found.
     * @throws DailyMixDataException
     *             If there's an issue with Daily Mix data.
     */
    private ArrayQueue<Song> readQueueFile(String songFileName)
        throws ParseException,
        FileNotFoundException,
        DailyMixDataException {
        ArrayQueue<Song> returnQueue = new ArrayQueue<Song>();
        Scanner file = new Scanner(new File(songFileName));
        while (file.hasNextLine()) {
            String read = file.nextLine();
            Scanner currLine = new Scanner(read).useDelimiter(",\\s*");
            String tokens[] = new String[5];
            int tokenCount = 0;
            while (currLine.hasNext() && tokenCount < 5) {
                tokens[tokenCount++] = currLine.next();
            }
            currLine.close();
            if (tokenCount >= 4) {
                if (tokens[4] == null || tokens[4].equals("None")) {
                    tokens[4] = "";
                }
                if (!this.isInValidPercentRange(Integer.valueOf(tokens[1]),
                    Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3]))) {
                    throw new DailyMixDataException(""); // TODO: Message for
                                                         // error
                }
                Song song = new Song(tokens[0], Integer.valueOf(tokens[1]),
                    Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3]),
                    tokens[4]);
                returnQueue.enqueue(song);
            }
            else {
                throw new ParseException("parse exception", 1);
            }
        }
        file.close();
        return returnQueue;
    }
}
