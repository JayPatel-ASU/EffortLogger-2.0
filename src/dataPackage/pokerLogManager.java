package dataPackage;

import java.io.*;
import java.util.ArrayList;

public class pokerLogManager {

    /**
     * Private Class Variables
     **/
    private final String LOGFILE = "pokerEstimations.csv";
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;


    /**
     * Default Constructor
     *
     * @throws IOException - Outputs an error if a file w/ the specified pathname doesn't exist
     */
    public pokerLogManager() throws IOException {
        // Instantiate file object
        file = new File(LOGFILE);

        // If logfile doesn't exist, initialize file
        if (!checkFile()) {
            createFile();
        }
    }

    /** Method Declarations **/

    /**
     * checkFile()
     * @return bool - T/F if file exists in directory
     */
    private boolean checkFile() {
        return file.exists();
    }

    /**
     * createFile()
     * Description: Creates a logfile in the current project directory. Used in constructor
     * @throws IOException - Outputs an error if a file w/ the specified pathname already exists
     */
    private void createFile() throws IOException{
        file.createNewFile();
        initializeFile();
    }

    private void initializeFile() throws IOException{
        // Init bufferedWriter object
        writer = new BufferedWriter(new FileWriter(LOGFILE, true));
        writer.write("Estimations for Planning Poker");
        writer.newLine();
        writer.write("session name, user story, average effort value, median effort value, highest frequency effort value");
        writer.newLine();
        writer.close();
    }

    public void addEstimations(String log) throws IOException{
        File oldName = new File("pokerEstimations.csv");
        File temp = new File("temp.csv");

        writer = new BufferedWriter(new FileWriter("temp.csv", true));
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));

        String colNames = "user story, average effort value, median effort value, highest frequency effort value";

        String currentLine = reader.readLine();

        // Iterate to the end of the file
        while((currentLine != null)) {
            writer.write(currentLine);
            writer.newLine();
            currentLine = reader.readLine();
        }

        // Reached EOF, write new log
        writer.write(log);

        writer.close();
        reader.close();

        file.delete();
        temp.renameTo(oldName);
        file = temp;
    }
}