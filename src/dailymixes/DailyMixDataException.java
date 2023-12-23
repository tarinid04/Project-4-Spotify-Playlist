package dailymixes;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * This is a custom exception class for Daily Mix data-related exceptions.
 * It extends the standard Exception class.
 * 
 * @author Tarini Duvvuri
 * @version 11.04.23
 */
@SuppressWarnings("serial")
public class DailyMixDataException extends Exception {
    /**
     * Constructs a new DailyMixDataException with the specified detail message.
     *
     * @param message
     *            The detail message that describes the exception.
     */
    public DailyMixDataException(String message) {
        super(message);
    }
}
