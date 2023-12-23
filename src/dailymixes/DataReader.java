package dailymixes;
//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I
//accept the actions of those who do.
//-- Tarini Duvvuri (tarinid)

import java.io.File;
import java.io.FileNotFoundException;
// import java.text.ParseException;
import java.util.Scanner;

/**
 * @author maellis1
 * @version March 2022
 */

public class DataReader {

    private String[] data;


    /**
     * Constructor reads planets in from a file and put them into data array
     * 
     * 
     * @param fileName
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public DataReader(String applicantFileName)
        throws java.text.ParseException,
        FileNotFoundException {
        data = readDataFile(applicantFileName);

    }


    /**
     * readDataFile The file fileName is parsed and strings are created
     * This method is expecting the file to have 3 comma separated values
     * per line of data and will process up to 10 lines of data.
     *
     * 
     * @param fileName
     * @throws ParseException
     * @throws FileNotFoundException
     */

    private String[] readDataFile(String fileName)
        throws FileNotFoundException,
        java.text.ParseException {

        data = new String[30];
        Scanner file = new Scanner(new File(fileName));
        int lineCount = 0;
        int dataCount = 0;
        while (file.hasNextLine() && lineCount < 10) {
            String read = file.nextLine();
            Scanner currLine = new Scanner(read).useDelimiter(",\\s*");
            String tokens[] = new String[3];
            int tokenCount = 0;
            while (currLine.hasNext() && tokenCount < 3) {
                tokens[tokenCount++] = currLine.next();
            }
            currLine.close();
            if (tokenCount == 3) {
                for (int i = 0; i < 3; i++)
                    data[dataCount++] = tokens[i];
            }
            else {
                throw new java.text.ParseException("parse exception", 1);
            }
            lineCount++;
        }

        file.close();
        return data;
    }

}
