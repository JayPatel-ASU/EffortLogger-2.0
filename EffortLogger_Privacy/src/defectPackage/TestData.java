package defectPackage;


/**
 * @author Jay Patel
 * TestData contains a test set of data for DefectConsole's prototype.
 */
public class TestData {

    /** Class Instance Variables **/
    private String projectName;
    private String defectName;
    private String description;

    /**
     * Default Constructor: Initializes instance variables
     */
    public TestData() {
        projectName = "TestProject01";
        defectName = "TestDefect01";
        description = "TestDescription";
    }

    /**
     * Method: setProjectName(String pName)
     * @param pName - Contains string that project name will be set to
     */
    public void setProjectName(String pName) {
        projectName = pName;
    }

    /**
     * Method: getProjectName()
     * Returns the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Method: setDefectName(String dName)
     */
    public void setDefectName(String dName) {
        defectName = dName;
    }

    /**
     * Method: getDefectName()
     * Returns the defect name
     */
    public String getDefectName() {
        return defectName;
    }

    /**
     * Method: setDescription(String desc)
     * @param desc - Contains string that description will be set to
     */
    public void setDescription(String desc) {
        description = desc;
    }

    /**
     * Method: setDescription(String desc)
     * Returns defect description
     */
    public String getDescription() {
        return description;
    }
}
