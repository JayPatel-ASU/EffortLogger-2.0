package consolePackage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import dataPackage.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.time.LocalTime;
import java.time.LocalDate;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * @author Jay Patel
 * Controller class that handles Effort Console's UI logic
 */
public class EffortConsoleController {

    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;

    LocalTime startTime;
    LocalTime endTime;
    LocalDate date;

    Data data;

    LogManager logManager;
    private boolean clockOn;
    @FXML
    Rectangle clockStatusRectangle;

    @FXML
    private Text clockStatusText;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private ComboBox<String> LCSComboBox;
    @FXML
    private ComboBox<String> ECComboBox;

    @FXML
    private ComboBox<String> planComboBox;

    /**
     * initialize()
     * Description: Initializes data fields on Effort Console startup
     * @throws IOException - Outputs an error if a file w/ the specified pathname already exists (Data/LogManager)
     */
    @FXML
    protected void initialize() throws IOException {
        // Initialize data & log manager objects
        data = new Data();
        logManager = new LogManager(data);

        // Initialize all combo boxes
        for (int i = 0; i < 4; i++) {
            initComboBox(data, i);
        }

        clockOn = false;  // Init clock on status to false
    }

    /**
     * initComboBox(Data, int)
     * Description: Initializes all combo box fields in Effort Console's UI
     * @param data - Contains parsed log data
     * @param category - Determines which category to retrieve data for
     */
    @FXML
    private void initComboBox(Data data, int category) {
        // Init array that temporarily stores data
        ArrayList<String> temp = data.getDefinitions(category);

        // Loop through every elem in data array & store in combo box
        for (int i = 0; i < temp.size(); i++) {
            if (category == 0)
                projectComboBox.getItems().add(temp.get(i));
            else if (category == 1)
                LCSComboBox.getItems().add(temp.get(i));
            else if (category == 2)
                ECComboBox.getItems().add(temp.get(i));
            else if (category == 3)
                planComboBox.getItems().add(temp.get(i));
        }
    }

    /**
     * onStartButtonClicked()
     * Description: Starts clock & updates UI when the start button is clicked
     */
    @FXML
    public void onStartButtonClicked() {
        // Start clock on button click
        startTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        // Get local date
        date = LocalDate.now();

        // Change clock status prompts on gui to Green/Running
        clockStatusRectangle.setFill(Color.GREEN);
        clockStatusText.setText("Clock is running");
        clockOn = true; // Set clock on status to true
    }

    /**
     * onStopButtonClicked()
     * Description: Stops the clock, updates UI, and updates logs when the stop button is clicked
     * @throws IOException - Outputs an error if a file w/ the specified pathname already exists (Data/LogManager)
     */
    @FXML
    public void onStopButtonClicked() throws IOException{
        // If clock is not on, don't do anything -- return
        if (!clockOn)
            return;

        // Get stop time
        endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        // Change clock status message
        clockStatusRectangle.setFill(Color.RED);
        clockStatusText.setText("Clock is stopped");

        // Get project name and log number
        String project = projectComboBox.getValue();
        int logNum = data.getNumLogs(data.getProjectNum(project)) + 1;

        // Format all pieces of data into a string, then store the information in effortlogs.csv
        String log = formatLog(project);

        // Update logs with new data
        logManager.updateLog(data.getProjectNum(project), logNum, log);

        // Reset logmanager, data
        data = new Data();
        logManager = new LogManager(data);
    }

    /**
     * formatLog(String)
     * Description: Formats a log that will be written to log file
     * @param project - Name of the project that will be logged
     * @return - String containing formatted log
     */
    private String formatLog(String project) {

        // Calculate difference in time
        String deltaTime = calculateDeltaTime();

        // Get values from each combo box
        String LCS = LCSComboBox.getValue();
        String effortCategory = ECComboBox.getValue();
        String plan = planComboBox.getValue();
        // Get the project number
        String logNum = Integer.toString(data.getNumLogs(data.getProjectNum(project)) + 1);

        // Store all pieces of data in a string
        String log = logNum + "," + date.toString() + "," + startTime.toString() + "," + endTime.toString() + "," + deltaTime + "," + LCS + "," + effortCategory
                + "," + plan + ",,,,,,,,,";

        return log;
    }

    /**
     * calculateDeltaTime()
     * Description: Calculates the time difference betwwen start/stop times
     * @return deltaTime - String that contains time delta
     */
    public String calculateDeltaTime() {
        long deltaMin = startTime.until(endTime, ChronoUnit.MINUTES);
        long deltaSec = startTime.until(endTime, ChronoUnit.SECONDS);

        String deltaSecStr; // Stores temp string, used if sec < 10

        // Modify string if sec < 10
        if (deltaSec < 10)
            deltaSecStr = "0" + Long.toString(deltaSec);
        // Sec > 10, no extra work needed -- convert to string
        else
            deltaSecStr = Long.toString(deltaSec);

        // Store total delta time (in minutes.seconds), then return
        String deltaTime = Long.toString(deltaMin) + "." + deltaSecStr;

        return deltaTime;
    }

}
