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

    Data data;

    LogManager logManager;

    /**
     * Method: initialize()
     * Description: Contains UI logic on application startup
     */
    @FXML
    protected void initialize() throws IOException {

        Data data = new Data();
        logManager = new LogManager(data);

        // Initialize project combo box
        ArrayList<String> projects = data.getDefinitions(0);
        // Set each item in combo box
        for (int i = 0; i < projects.size(); i++) {
            projectList.getItems().add(projects.get(i));
        }

        // Initialize injection steps list
        // TODO -- update to store variable locations on , FIX
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
    protected void onUpdateDefectClick() {

        String dName =  defectName.getText();
        String desc = defectDescription.getText();

        String project = projectList.getValue();
        String injectStep = injectSteps.getSelectionModel().getSelectedItem();
        String removeStep = removedSteps.getSelectionModel().getSelectedItem();
        String defectCat = defectCategory.getSelectionModel().getSelectedItem();

        defectList.getItems().set(defectList.getSelectionModel().getSelectedIndex(), dName);

    }

    /**
     * Method: createNewDefect()
     * Description: Button that adds a new defect to the defect list when clicked
     */
    @FXML
    protected void createNewDefectOnClick() {
        defectList.getItems().add("New Defect");
    }

    /**
     * Method: clearLogOnClick()
     * Description: Logic for the clear defect logs button
     */
    @FXML
    protected void clearLogOnClick() {
        defectList.getItems().clear();
    }

}