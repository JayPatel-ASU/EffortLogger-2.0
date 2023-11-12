package consolePackage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import dataPackage.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import privacyPackage.Log;

import java.time.LocalTime;
import java.time.LocalDate;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * @author Jay Patel
 *
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
     * Description:
     * @param data
     * @param category
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

        //
        String project = projectComboBox.getValue();
        int logNum = data.getNumLogs(getProjectNum(project)) + 1;

        // Format all pieces of data into a string, then store the information in effortlogs.csv
        String log = formatLog(project);

        logManager.addLog(data, getProjectNum(project), logNum, log);

        // Reset logmanager, data TODO TEMP FIX
        data = new Data();
        logManager = new LogManager(data);
    }

    private String formatLog(String project) {

        // Calculate difference in time
        String deltaTime = calculateDeltaTime();

        // Get values from each combo box
        String LCS = LCSComboBox.getValue();
        String effortCategory = ECComboBox.getValue();
        String plan = planComboBox.getValue();
        // Get the project number
        String logNum = Integer.toString(data.getNumLogs(getProjectNum(project)) + 1);

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

    public int getProjectNum(String projectName) {
        // Case: User forgot
        if (projectName == null) {
            return 0;
        }

        // Init project num and array that holds all project names
        int projectNum = 0;
        ArrayList<String> projectNames = data.getDefinitions(0);

        for (int i = 0; i < projectNames.size(); i++) {
            if (projectName.equals(projectNames.get(i)))
                projectNum = i;
        }
        return projectNum;
    }
}
