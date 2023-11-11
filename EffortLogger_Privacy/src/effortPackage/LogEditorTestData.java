package effortPackage;


/**
 * @author Jay Patel
 * TestData contains a test set of data for DefectConsole's prototype.
 */
public class LogEditorTestData {

    /** Class Instance Variables **/
    private String date;
    private String time;
    private String startTime;
    private String stopTime;

    /**
     * Default Constructor: Initializes instance variables
     */
    public LogEditorTestData() {
        date = "";
        time = "";
        startTime = "";
        stopTime = "";
        deltaTime = "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(String deltaTime) {
        this.deltaTime = deltaTime;
    }

    private String deltaTime;


}
