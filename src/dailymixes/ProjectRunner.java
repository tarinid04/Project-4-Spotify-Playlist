package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * The main class that runs the playlist generation project.
 * It initializes the necessary components, including data reading and the user
 * interface window.
 *
 * @author tarinid
 */
public class ProjectRunner {
    // Virginia Tech Honor Code Pledge:
    //
    // As a Hokie, I will conduct myself with honor and integrity at all times.
    // I will not lie, cheat, or steal, nor will I
    // accept the actions of those who do.
    // -- Tarini Duvvuri (tarinid)

    /**
     * Constructor for the ProjectRunner class.
     */
    public ProjectRunner() {
        // TODO Auto-generated constructor stub
    }


    /**
     * The main entry point for the program. It initializes the playlist reader,
     * calculator,
     * and user interface window, and then starts the application.
     *
     * @param args
     *            Command-line arguments. The first argument is the input songs
     *            file, and the second
     *            argument is the input playlists file.
     * @throws DailyMixDataException
     *             If there is an issue with the daily mix data.
     * @throws ParseException
     *             If there is an error in parsing the input data.
     * @throws FileNotFoundException
     *             If the input files are not found.
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException {
        PlaylistReader reader;
        if (args.length == 2) {
            reader = new PlaylistReader(args[0], args[1]);
        }
        else {
            reader = new PlaylistReader(
                "/SpotifyPlaylist/src/dailymixes/input.txt",
                "/SpotifyPlaylist/src/dailymixes/playlists.txt");
        }
        System.out.print(reader);
        PlaylistCalculator calculator = new PlaylistCalculator(reader.queue,
            reader.playlists);
        @SuppressWarnings("unused")
        PlaylistWindow window = new PlaylistWindow(calculator);
    }
}
