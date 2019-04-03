/**
 *  Is the FileUtility where we manipulate
 *  the files
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.ArrayList;


public class FileUtility
{

    /**
     * Read data from a file
     * @see demo lecture 8 Car Part Basics file handling demo file FileUtility
     * @param fileName
     * @return the saved list of Strings that was retrieved from the file
     * @throws IOException something when going wrong with reading the data from the file
     * @throws FileNotFoundException if the file doesn't exist
     */
    public static ArrayList<String> readFromFile(String fileName) throws IOException, FileNotFoundException {
        // create an empty array of Strings
        ArrayList<String> readData = new ArrayList<String>();

        // Read the file
        BufferedReader inTheFil = new BufferedReader(new FileReader(fileName));

        // Read the every line
        String textT = inTheFil.readLine();

        // If the line is not empty
        while(textT != null) {
            readData.add(textT);
            textT = inTheFil.readLine();
        }

        inTheFil.close();

        return readData;
    }

    /**
     * Write data to file 
     * @see demo lecture 8 Car Part Basics file handling demo file FileUtility
     * @param fileName the file name
     * @param data the data that is going to be written in the file
     * @throws IOException something when going wrong with write data to the file 
     * @throws FileNotFoundException if the file doesn't exist
     */
    public static void writeToFile(String fileName, ArrayList<String> data) throws IOException, FileNotFoundException {
 
        PrintWriter saveDa = new PrintWriter(new FileWriter(fileName));
        
        for(String writeSt : data){
            saveDa.println(writeSt);
        }
        
        saveDa.close();
        
    }

}