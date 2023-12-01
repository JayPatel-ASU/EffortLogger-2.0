/**
 * LogManager
 * Description: Class that interacts with effortlogs.csv and definitions.ini
 * Author: Jay Patel
 */

package dataPackage;

import java.io.*;
import java.util.ArrayList;

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

        // Read from logfile, store defect info in Data class
        parseDefectData(Data);
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
     * parseDefectData(Data)
     * @param Data - Stores parsed data from logfile
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void parseDefectData(Data Data) throws IOException {
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
            if (currentLine.substring(0, 1).equals(",")) {
                Data.storeDefectData(projectCounter - 1 , currentLine);
            }
        }
        reader.close(); // Done reading from file, close reader
    }

    /**
     * parseDefinitions(Data)
     * Description: Parses data from definitions.ini and stores it in the Data object
     * @param Data - Object that definition data will be stored to
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void parseDefinitions(Data Data) throws IOException {
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

    /**
     * updateLog(int, int, String)
     * Description: Updates effortlogs.csv with the specified log
     * @param projectNum - Int containing the project number
     * @param logNum - Int containing the log number
     * @param log - String containing the log to be updated
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void updateLog(int projectNum, int logNum, String log) throws IOException {

        // Buffered read/writer classes can't update an existing file?? Instead, create a temp file and rename
        // Init file names
        File oldName = new File("effortlogs.csv");
        File temp = new File("temp.csv");

        // Init buffered writer/reader objects
        writer = new BufferedWriter(new FileWriter("temp.csv", true));
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));

        // Calculation for line to overwrite
        int lineToOverwrite = projectNum*1000 + 3 + logNum;

        String currentLine = reader.readLine();
        int lineCount = 0;

        // Loop until the end of the file
        while((currentLine != null)) {

            lineCount++;
            // Write new log when program reaches line to overwrite
            if (lineCount == lineToOverwrite) {
                writer.write(log);
            }
            // If not the line we need to overwrite, write from the original log file
            else {
                writer.write(currentLine);
            }
            writer.newLine();
            currentLine = reader.readLine(); // Read next line
        }
        // Close buffered reader/writer
        writer.close();
        reader.close();

        // Rename temp to effortlogs.csv
        file.delete();
        temp.renameTo(oldName);
        file = temp;
    }

    /**
     * clearProjectLogs(int)
     * Description: Clears all logs for a user-specified project
     * @throws IOException - Exception thrown if files do not exist in directory
     */
    public void clearProjectLogs(int projectNum) throws IOException {

        // Init file names, empty string
        File oldName = new File("effortlogs.csv");
        File temp = new File("temp.csv");

        String emptyLine = ",,,,,,,,,,,,,,,,,";

        // Init buffered writer/reader objects
        writer = new BufferedWriter(new FileWriter("temp.csv", true));
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));


        // Calculation for line to overwrite
        String currentLine = reader.readLine();
        int lineCount = 0;
        int projectCount = -1;

        // Loop until end of file is reached
        while((currentLine != null)) {
            lineCount++;
            // If new project line is found, increment project count
            if (currentLine.contains("Effort Log for Project"))
                projectCount++;

            // Do not clear lines that contain column info
            if (projectCount == projectNum) {
                if (lineCount % 1000 == 1 || lineCount % 1000 == 2 || lineCount % 1000 == 3)
                    writer.write(currentLine);
                // Line doesn't contain column info -- write an empty line
                else
                    writer.write(emptyLine);
            }
            // Not writing to current project -- copy from original file
            else {
                writer.write(currentLine);
            }
            writer.newLine();
            currentLine = reader.readLine();
        }

        // Close reader, writer buffers
        writer.close();
        reader.close();

        // Delete old .csv file, & rename temp.csv
        file.delete();
        temp.renameTo(oldName);
        file = temp;
    }

    /**
     * deleteLog(int, int)
     * Description: Clears a single log from effortlogs.csv
     * @param projectNum - Int containing the project number
     * @param logNum - Int containing the log number
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public void deleteLog(int projectNum, int logNum) throws IOException {
       String emptyLog = ",,,,,,,,,,,,,,,,,";
       updateLog(projectNum, logNum, emptyLog);
    }

    /**
     * getLogNum(int, String)
     * Description: Retreives the log number for a specific log in effortlogs.csv
     * @param projectNum - Int containing the project number
     * @param log - String containing a log from effortlogs.csv
     * @return - Int - Returns the log number (from effortlogs.csv)
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public int getLogNum(int projectNum, String log) throws IOException{

        // Initialize BufferedReader, currentline, and logNum
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));
        String currentLine = reader.readLine();
        int logNum = 0; // Will be returned

        // Loop until reader reaches EOF
        while (currentLine != null) {
            logNum++; // Increment log counter for every line
            if (currentLine.equals(log))
                break; // If log is found, break from loop
            else
                currentLine = reader.readLine(); // Log not found -- read next line
        }
        reader.close();

        // Equation for retrieving correct log number
        logNum = (logNum % ((projectNum + 1) * 1000)) - 3;

        return logNum;
    }

    /**
     * getDefectNum(int,String,Data)
     * Description: Retreives the defect number for a specific log in effortlogs.csv
     * @param projectNum - Int containing the project number
     * @param logName - String containing the log name
     * @param data - Contains parsed data
     * @return - Int containing the defect log number
     * @throws IOException - Exception thrown if file does not exist in directory
     */
    public int getDefectNum(int projectNum, String logName, Data data) throws IOException{

        // Initialize BufferedReader, currentline, and logNum
        reader = new BufferedReader(new BufferedReader(new FileReader(LOGFILE)));
        String currentLine = reader.readLine();
        int logNum = 0; // Will be returned

        // Loop until reader reaches EOF
        while (currentLine != null) {
            logNum++; // Increment log counter for every line
            if (currentLine.contains(logName))
                break; // If log is found, break from loop
            else
                currentLine = reader.readLine(); // Log not found -- read next line
        }
        reader.close();

        // Equation for retrieving correct log number
        logNum = (logNum % ((projectNum + 1) * 1000)) - 3;

        return logNum;
    }
}