import java.util.ArrayList;

/**
 * Class: Data
 * Description: Class that stores, manages, and interfaces with data from the logfile.
 * Author: Jay Patel
 */

public class Data {

    private final int PROJECTCOUNT = 10;
    private ArrayList<ArrayList<String>> logData;
    private ArrayList<ArrayList<String>> definitions;

    /**
     * Default Constructor
     */
    public Data() {
        initializeLogArray();
        initializeDefArray();
    }

    /**
     * TODO - COMMENT
     */
    private void initializeLogArray() {
        // Init 1st dimension of array (size 10)
        logData = new ArrayList<>(PROJECTCOUNT);

        // Initialize 2nd dimension of each index
        for (int i = 0; i < PROJECTCOUNT; i++) {
            logData.add(new ArrayList<>());
        }
    }

    /**
     * TODO: METHOD COMMENTS
     */
    private void initializeDefArray() {
        //TODO: INITIALIZE DEFINITIONS ARRAY
    }

    /**
     * storeLogData(int, String)
     * Description: TODO: WRITE DESCRIPTION
     * @param projectNum
     * @param currentLine
     */
    public void storeLogData(int projectNum, String currentLine) {
        logData.get(projectNum).add(currentLine);
    }

    /**
     * TODO
     */
    void printLogData() {
        for (int i = 0; i < logData.size(); i++) {
            for (int j = 0; j < logData.get(i).size(); j++) {
                System.out.println(logData.get(i).get(j));
            }
        }
    }

    //public String[] getProjectData(int projectNum) {} //TODO: IMPLEMENT
}
