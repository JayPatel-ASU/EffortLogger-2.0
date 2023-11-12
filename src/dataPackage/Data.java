package dataPackage;

import java.util.ArrayList;

/**
 * Class: Data
 * Description: Class that stores, manages, and interfaces with data from the logfile.
 * Author: Jay Patel
 */

public class Data {

    /** Private Class Variables **/

    private ArrayList<ArrayList<String>> logData;
    private ArrayList<ArrayList<String>> definitions;

    /**
     * Default Constructor
     */
    public Data() {
        initializeLogArray();
        initializeDefArray();
    }

    /** Method Declarations **/

    /**
     * initializeLogArray()
     * Description: Allocates memory for log data array. Used in constructor
     */
    private void initializeLogArray() {
        // Init 1st dimension of array (size 10)
        logData = new ArrayList<>(10);

        // Initialize 2nd dimension of each index
        for (int i = 0; i < 10; i++) {
            logData.add(new ArrayList<>());
        }
    }

    /**
     * initializeDefArray()
     * Description: Allocates memory for definitions array. Used in constructor
     */
    private void initializeDefArray() {

        // Initialize first dimension of definitions array
        definitions = new ArrayList<>(7); // 7 Definition categories

        // Initialize 2nd dimension of each index
        for (int i = 0; i < 7; i++) {
            definitions.add(new ArrayList<>());
        }
    }

    /**
     * getDefinitions(int)
     * Description: Returns a list of definitions based on the category parameter
     *         0 = Project name
     *         1 = Life Cycle Steps
     *         2 = Effort Categories
     *         3 = Plans
     *         4 = Deliverables
     *         5 = Interruptions
     *         6 = Defect Categories
     * @param category - Definition category (0 - 6)
     * @return returnArr - Arraylist of definitions for specified category
     */
    public ArrayList<String> getDefinitions(int category){
        ArrayList<String> returnArr = new ArrayList<>(); // Stores definitions, will be returned

        // For each definition in category, append info into return array
        for (int i = 0; i < definitions.get(category).size(); i++) {
            returnArr.add(definitions.get(category).get(i));
        }
        return returnArr;
    }

    /**
     * storeLogData(int, String)
     * Description: Stores log data for each specified line in logfile.csv
     * @param projectNum
     * @param currentLine
     */
    public void storeLogData(int projectNum, String currentLine) {
        logData.get(projectNum).add(currentLine);
    }

    /**
     * storeDefinitionData(int, String)
     * Description: Stores definition data for each specified category in definitions.ini
     * @param category - Int containing which category to store data to
     * @param currentLine - String containing the current line of data
     */
    public void storeDefinitionData(int category, String currentLine) {
        definitions.get(category).add(currentLine);
    }

    /**
     * getLogData(int)
     * @param projectNum -
     * @return -
     */
    /*
    public ArrayList<String> getLogData(int projectNum){

        // Set size of return list
        ArrayList<String> returnArr = new ArrayList<>(logData.size());

    }*/

    /**
     *
     * Description: Gets number of logs for a specific project
     * @param projectNum
     * @return
     */
    public int getNumLogs(int projectNum) {
        return logData.get(projectNum).size();
    }

    /**
     * TODO REMOVE PRINT FUNCTIONS
     */
    void printLogData() {
        for (int i = 0; i < logData.size(); i++) {
            for (int j = 0; j < logData.get(i).size(); j++) {
                System.out.println(logData.get(i).get(j));
            }
        }
    }

    void printDefData() {
        for (int i = 0; i < definitions.size(); i++) {
            for (int j = 0; j < definitions.get(i).size(); j++) {
                System.out.println(definitions.get(i).get(j) + " ");
            }
            System.out.println("---------------------------------------------");
        }
    }


    //public String[] getProjectData(int projectNum) {} //TODO: IMPLEMENT
}
