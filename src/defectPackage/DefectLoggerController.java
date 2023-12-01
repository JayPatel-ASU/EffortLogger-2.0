package defectPackage;

import dataPackage.Data;
import dataPackage.LogManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jay Patel
 * DefectLoggerController contains the logic for the Defect Console's UI. References DefectLoggerController.fxml
 */
public class DefectLoggerController {

    /** Class Instance Variables **/

    @FXML
    private ComboBox<String> projectList;

    @FXML
    private ComboBox<String> defectList;

    @FXML
    private TextField defectName;

    @FXML
    private TextField defectDescription;

    @FXML
    private ListView<String> injectSteps;

    @FXML
    private ListView<String> removedSteps;

    @FXML
    private ListView<String> defectCategory;

    @FXML
    private Label updatedLabel;

    private Data data;

    private LogManager logManager;

    /**
     * Method: initialize()
     * Description: Contains UI logic on application startup
     */
    @FXML
    protected void initialize() throws IOException {

        data = new Data();
        logManager = new LogManager(data);

        // Initialize project combo box
        ArrayList<String> projects = data.getDefinitions(0);
        // Set each item in combo box
        for (int i = 0; i < projects.size(); i++) {
            projectList.getItems().add(projects.get(i));
        }

        // Initialize injection steps list
        ArrayList<String> steps = data.getDefinitions(1);
        for (int i = 22; i < 26; i++){
            injectSteps.getItems().add(steps.get(i));
        }

        for (int i = 16; i < 26; i++) {
            removedSteps.getItems().add(steps.get(i));
        }

        ArrayList<String> defects = data.getDefinitions(6);

        for (int i = 0; i < defects.size(); i++) {
            defectCategory.getItems().add(defects.get(i));
        }
    }

    /**
     * Method: onUpdateDefectClick()
     * Description: Contains logic when the "Update the defect" button is clicked
     */
    @FXML
    protected void onUpdateDefectClick() throws IOException{

        data = new Data();
        logManager = new LogManager(data);

        String oldName = defectList.getSelectionModel().getSelectedItem();

        String dName =  defectName.getText();
        String desc = defectDescription.getText();

        String project = projectList.getValue();

        // If any element in defect logger is null, return
        if (oldName == null || dName == null || desc == null || project == null || defectList.getSelectionModel().getSelectedItem() == null)
            return;

        String log = formatLog(project);

        if (oldName.equals("New Defect")) {

            int logNum = data.getNumLogs(getProjectNum(project)) + 1;
            defectList.getItems().set(defectList.getSelectionModel().getSelectedIndex(), dName);

            logManager.updateLog(getProjectNum(project), logNum, log);
        }

        else {
            int logNum = logManager.getDefectNum(getProjectNum(project), oldName, data);
            logManager.updateLog(getProjectNum(project), logNum, log);
        }
    }

    private String formatLog(String project) {

        String dName =  defectName.getText();

        String desc = defectDescription.getText();

        String injectStep = injectSteps.getSelectionModel().getSelectedItem();
        String removeStep = removedSteps.getSelectionModel().getSelectedItem();
        String defectCat = defectCategory.getSelectionModel().getSelectedItem();
        // Get the project number
        String logNum = Integer.toString(data.getNumLogs(getProjectNum(project)) + 1);

        // Store all pieces of data in a string
        String log = ",,,,,,,,,," + logNum + "," + dName + "," + desc + "," + injectStep + "," + removeStep + "," + defectCat
                + "," + "Open" + "," + "N/A";

        return log;
    }

    /**
     * Method: createNewDefect()
     * Description: Button that adds a new defect to the defect list when clicked
     */
    @FXML
    protected void createNewDefectOnClick() {
        defectList.getItems().add("New Defect");
        defectList.getSelectionModel().selectLast();
    }

    /**
     * Method: clearLogOnClick()
     * Description: Logic for the clear defect logs button
     */
    @FXML
    protected void clearLogOnClick() throws IOException{
        // Get the current log info from logComboBox/projectComboBox
        String selectedProject = projectList.getSelectionModel().getSelectedItem();
        String selectedDefect = defectList.getSelectionModel().getSelectedItem();

        // Return if selectedProject or selectedDefect is null
        if (selectedProject == null || selectedDefect == null)
            return;

        // Set the projectNum, logNum
        int projectNum = data.getProjectNum(selectedProject);
        int logNum = logManager.getDefectNum(projectNum, selectedDefect, data);
        // Delete the log
        logManager.deleteLog(projectNum, logNum);

        // Update data and LogManager values
        data = new Data();
        logManager = new LogManager(data);

        // Update logComboBox to represent current logs
        updateDefectList();
    }
    public int getProjectNum(String projectName) {

        // Init project num and array that holds all project names
        int projectNum = 0;
        ArrayList<String> projectNames = data.getDefinitions(0);

        for (int i = 0; i < projectNames.size(); i++) {
            if (projectName.equals(projectNames.get(i)))
                projectNum = i;
        }
        return projectNum;
    }

    /**
     * ProjListSetOnAction()
     * Description: Updates the defect list field when an action is made on the projectList
     */
    @FXML
    public void ProjListSetOnAction() {
        updateDefectList();
    }

    private void updateDefectList() {
        ArrayList<String> names = parseDefectLogs();

        // Clear existing defects from other projects before assigning new values
        defectList.getItems().clear();

        // Populate log Combo box with entries from logs arrayList
        for (int i = 0; i < names.size(); i++) {
            defectList.getItems().add(names.get(i));
        }
        defectList.getSelectionModel().selectLast();
    }

    private ArrayList<String> parseDefectLogs() {
        // On action, get selected project's number & update corresponding log combo box
        String selectedProject = projectList.getSelectionModel().getSelectedItem();

        // Get the project number from the definitions array
        int projectNum = data.getProjectNum(selectedProject);

        //
        ArrayList<String> defects = data.getDefectData(projectNum);
        ArrayList<String> returnList = new ArrayList<>();

        for (int i = 0; i < defects.size(); i++) {
            String current = defects.get(i);

            current = current.replace(",,,,,,,,,,", "");
            current = current.replace(current.substring(0, current.indexOf(",") + 1), "");
            current = current.substring(0, current.indexOf(","));

            returnList.add(current);
        }
        return returnList;
    }
}