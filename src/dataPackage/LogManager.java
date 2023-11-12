package dataPackage;

import java.io.*;
import java.util.ArrayList;

/**
 * LogManager
 * Description: Class that interacts with effortlogs.csv and definitions.ini
 * Author: Jay Patel
 */

public class LogManager {

    /** Private Class Variables **/
    private final String LOGFILE = "effortlogs.csv";
    private final String DEFINITIONSFILE = "definitions.ini";
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * Parameterized Constructor
     * @param Data - Object that contains effortLog/definition data
     * @throws IOException - Outputs an error if a file w/ the specified pathname doesn't exist
     */
    public LogManager(Data Data) throws IOException {
        // Instantiate file object
        file = new File(LOGFILE);

        // If logfile doesn't exist, initialize file
        if (!checkFile()) {
            createFile();
            initializeFile();
        }

        // Read data from definitions, store info in Data class
        parseDefinitions(Data);

        // Read from logfile, store info in Data class
        parseLogData(Data);
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
    }

    /**
     * initializeFile()
     * Description: Initializes a new logfile if one doesn't currently exist in the project directory. Called only in
     *              the constructor.
     */
    private void initializeFile() throws IOException {

        // Init bufferedWriter object
        writer = new BufferedWriter(new FileWriter(LOGFILE, true));
        // Counter for # of projects (MAX: 10)
        int projectCounter = 1;

        String emptyLine = ",,,,,,,,,,,,,,,,,";

        // Loop through every line in file, supports ~1000 log entries per project
        for (int i = 0; i < 10000; i++) {
            // First line for project log -- Write column info for current project #
            if (i % 1000 == 0) {
                // Initial project info line
                writer.write("Effort Log for Project " + projectCounter + ": ,,Business Project,,,Number of Entries: ,,," +
                        ",Defect Log for Project " + projectCounter + ":,,Business Project,Number of Entries: ,,,,,");

                // Increment project counter
                projectCounter++;
            }
            // Case: Line 2 - Write col info for Time, Delta, Effort
            else if (i % 1000 == 1) {
                writer.write(",,Time,,Delta,,Effort,,,,,,,,,,,");
            }
            // Case: Line 3 - Write details for data in each col
            else if (i % 1000 == 2) {
                writer.write("Number,Date,Start,Stop,Time,Life Cycle Step,Category,Deliverable / Interruption / etc.,,Number," +
                        "Name,Detail,Injected,Removed,Category,Status,Fix,");
            }

            // In all other cases, write an empty line to file (no existing logs)
            else {
                writer.write(emptyLine);
            }
            writer.newLine(); // Get next line
        }
        writer.close(); // Close file
    }

    /**
     * updateLog(int, ArrayList<String>)
     * @param newLog TODO UPDATE COMMENT
     */
    public void updateLog(int projectNum, ArrayList<String> newLog) throws IOException{
        //TODO - FINISH METHOD
        int startPosition = (projectNum - 1) * 1000;
        writer = new BufferedWriter(new FileWriter(LOGFILE, true));

        writer.close();
    }

    /**
     * parseLogData(Data)
     * Description: Parses data from effortlogs.csv and stores it in the Data object
     * @param Data - Object that contains effort data
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void parseLogData(Data Data) throws IOException{

        reader = new BufferedReader(new FileReader(LOGFILE)); // Instantiate new reader object
        int projectCounter = 0; // Keeps track of # of projects

        // Loop through every line in .csv file
        for (int i = 0; i < 10000; i++) {
            // Read current line, then store for parsing
            String currentLine = reader.readLine();

            // If current line has column info, no need to parse -- continue
            if (i % 1000 == 0 || i % 1000 == 1 || i % 1000 == 2) {
                // Increment project counter every 1000 lines
                if (i % 1000 == 0)
                    projectCounter++;
                continue;
            }

            // Empty line - no need to parse
            if (currentLine.equals(",,,,,,,,,,,,,,,,,") || currentLine.isEmpty()) {
                continue;
            }
            // Store line into data
            Data.storeLogData(projectCounter - 1 , currentLine);
        }
        reader.close(); // Done reading from file, close reader
    }

    /**
     * parseDefinitions(Data)
     * Description: Parses data from definitions.ini and stores it in the Data object
     * @param Data - Object that definition data will be stored to
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void parseDefinitions(Data Data) throws IOException{
        reader = new BufferedReader(new FileReader(DEFINITIONSFILE)); // Instantiate new reader object
        int categoryNum = 0; // Tracks project number to update
        String currentLine = reader.readLine();

        // Keep reading until end of file has been reached
        while (currentLine != null) {

            // If the current line has a data element to store
            if (currentLine.contains(" =")) {
                // If the line contains 01, we know it's the data for a new definition category
                if (currentLine.contains("01"))
                    categoryNum++;

                // Create substring of data, then store into Definitions array
                currentLine = currentLine.substring(currentLine.indexOf("=") + 1);

                // If data isn't empty, store in definitions array
                if (!currentLine.isEmpty())
                    Data.storeDefinitionData(categoryNum - 1, currentLine);
            }
            // Get next line
            currentLine = reader.readLine();
        }
        reader.close(); // Done reading from file, close reader
    }

    public void addLog(Data Data, int projectNum, int logNum, String log) throws IOException{

        File oldName = new File("effortlogs.csv");
        File temp = new File("temp.csv");

        writer = new BufferedWriter(new FileWriter("temp.csv", true));
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));

        int lineToOverwrite = projectNum*1000 + 3 + logNum;

        String currentLine = reader.readLine();
        int lineCount = 0;

        while((currentLine != null)) {

            lineCount++;
            if (lineCount == lineToOverwrite) {
                writer.write(log);
            }
            else {
                writer.write(currentLine);
            }
            writer.newLine();
            currentLine = reader.readLine();
        }
        writer.close();
        reader.close();

        file.delete();
        temp.renameTo(oldName);
        file = temp;
    }

    /**
     * deleteLog()
     * Description:
     */
    public void deleteLog() {
        // TODO - LOG DELETION
        // Clear line, then replace with just empty string
        // Remove from data array
        // Decrement #entries from project effort log counter
        // ",,,,,,,,,,,,,,,,,"
    }

}