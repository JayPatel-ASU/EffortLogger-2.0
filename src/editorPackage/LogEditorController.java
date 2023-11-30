package editorPackage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import dataPackage.*;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jay Patel
 * Controller class that handles Log Editor's UI logic
 */
public class LogEditorController {

    private Data data;

    private LogManager logManager;

    @FXML
    private Button clearButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private ComboBox<String> logComboBox;

    @FXML
    private ComboBox<String> LCSComboBox;

    @FXML
    private ComboBox<String> ECComboBox;

    @FXML
    private TextField dateField;

    @FXML
    private TextField startField;

    @FXML
    private TextField stopField;

    /**
     *
     * @throws IOException
     */
    @FXML
    protected void initialize() throws IOException {
        // Initialize data & log manager objects
        data = new Data();
        logManager = new LogManager(data);

        // Initialize all (definition) combo boxes
        for (int i = 0; i < 4; i++) {
            initComboBox(data, i);
        }
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
        }
    }

    @FXML
    public void onClearLogsClicked() throws IOException{
        // Get selected project's name & corresponding project number
        String selectedProject = projectComboBox.getSelectionModel().getSelectedItem();

        // Get the project number from the definitions array
        int projectNum = data.getProjectNum(selectedProject);

        logManager.clearProjectLogs(projectNum);

        // Reset logmanager, data
        data = new Data();
        logManager = new LogManager(data);

    }

    @FXML
    public void onUpdateEntryClicked() throws IOException{

        // Initialize project & set up formatted log
        String project = projectComboBox.getSelectionModel().getSelectedItem();
        String log = formatLog(project);

        // If project not selected, don't do anything -- return
        if (project == null || log == null)
            return;

        // Get project, lognum before updating the log
        String oldLog = logComboBox.getSelectionModel().getSelectedItem();
        int projectNum = data.getProjectNum(project);
        int logNum = logManager.getLogNum(projectNum, oldLog);

        logManager.updateLog(projectNum, logNum, log);

        // Reset logmanager, data
        data = new Data();
        logManager = new LogManager(data);
        updateLogComboBox();
    }

    private String formatLog(String project) throws IOException{

        // Grab information from text fields/combo boxes
        String date = dateField.getText();
        String startTime = startField.getText();
        String stopTime = stopField.getText();

        // Initialize variables used in combo boxes
        String oldLog = logComboBox.getSelectionModel().getSelectedItem();
        String LCStep = LCSComboBox.getSelectionModel().getSelectedItem();
        String effortCategory = ECComboBox.getSelectionModel().getSelectedItem();

        // If any combo box is null, don't do anything -- return null
        if (oldLog == null || LCStep == null || effortCategory == null)
            return null;

        int projectNum = data.getProjectNum(project);
        String logNum = Integer.toString(logManager.getLogNum(projectNum, oldLog));

        // Received all information -- create formatted log
        String log = logNum + "," + date + "," + startTime + "," + stopTime + "," + stopTime + "," + LCStep + "," + effortCategory
                + ",,,,,,,,,,,";

        // Formatting complete - return log
        return log;
    }

    /**
     *
     * @throws IOException
     */
    @FXML
    public void onDeleteEntryClicked() throws IOException{
        // Get the current log info from logComboBox/projectComboBox
        String selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
        String selectedLog = logComboBox.getSelectionModel().getSelectedItem();

        // If a log has been selected
        if (selectedLog != null) {

            // Set the projectNum, logNum
            int projectNum = data.getProjectNum(selectedProject);
            int logNum = logManager.getLogNum(projectNum, selectedLog);

            // Delete the log
            logManager.deleteLog(projectNum, logNum);

            // Update data and LogManager values
            data = new Data();
            logManager = new LogManager(data);

            // Update logComboBox to represent current logs
            updateLogComboBox();
        }
    }

    /**
     * ProjComboSetOnAction()
     * Description: Updates the logComboBox field when an action is made on the projectComboBox
     */
    @FXML
    public void ProjComboSetOnAction() {
        updateLogComboBox();
    }

    /**
     * updateLogComboBox()
     * Description: Updates the Log Combo Box field based on the project selected
     */
    private void updateLogComboBox() {
        // On action, get selected project's number & update corresponding log combo box
        String selectedProject = projectComboBox.getSelectionModel().getSelectedItem();

        // Get the project number from the definitions array
        int projectNum = data.getProjectNum(selectedProject);

        // Get list of logs
        ArrayList<String> logs = data.getLogData(projectNum);

        // Clear existing logs from other projects before assigning new values
        logComboBox.getItems().clear();

        // Populate log Combo box with entries from logs arrayList
        for (int i = 0; i < logs.size(); i++) {
            // Set temp, used to check if the current log is a defect log
            String temp = logs.get(i).substring(0,1);

            // If log is not a defect log, add it to the combo box
            if (!temp.equals(",")) {
                logComboBox.getItems().add(logs.get(i));
            }
        }
    }
}
