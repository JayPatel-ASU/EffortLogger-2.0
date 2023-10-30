package defectPackage;

/**
 * @author Jay Patel
 * TestData contains a test set of data for DefectConsole's prototype.
 */
public class TestData {

    /** Class Instance Variables **/
    private String projectName;
    private String DefectName;
    private String Description;

    /**
     * Default Constructor: Initializes instance variables
     */
    public TestData() {
        projectName = "TestProject01";
        DefectName = "TestDefect01";
        Description = "TestDescription";
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
        DefectName = dName;
    }

    /**
     * Method: getDefectName()
     * Returns the defect name
     */
    public String getDefectName() {
        return DefectName;
    }

    /**
     * Method: setDescription(String desc)
     * @param desc - Contains string that description will be set to
     */
    public void setDescription(String desc) {
        Description = desc;
    }
}
