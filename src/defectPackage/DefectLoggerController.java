package defectPackage;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * @author Jay Patel
 * DefectLoggerController contains the logic for the Defect Console's UI. References DefectLoggerController.fxml
 */
public class DefectLoggerController {

    /** Class Instance Variables **/
    private TestData userData;
    @FXML
    private ListView<String> projectList;

    @FXML
    private TextField defectName;

    @FXML
    private ListView<String> existingDefectList;

    @FXML
    private TextField defectDescription;

    @FXML
    private Label updatedLabel;

    /**
     * Method: onUpdateDefectClick()
     * Description: Contains logic when the "Update the defect" button is clicked
     */
    @FXML
    protected void onUpdateDefectClick() {
        updatedLabel.setVisible(true);
        userData.setDefectName(defectName.getText());
        userData.setDescription(defectDescription.getText());
        existingDefectList.getItems().set(0, userData.getDefectName());
    }

    /**
     * Method: initialize()
     * Description: Contains UI logic on application startup
     */
    @FXML
    protected void initialize() {
        userData = new TestData();
        //projectList.getItems().add(userData.getProjectName());
        //existingDefectList.getItems().add(userData.getDefectName());
    }

    /**
     * Method: createNewDefect()
     * Description: Button that adds a new defect to the defect list when clicked
     */
    @FXML
    protected void createNewDefect() {
        existingDefectList.getItems().add("New Defect01");
    }

    /**
     * Method: clearLogOnClick()
     * Description: Logic for the clear defect logs button
     */
    @FXML
    protected void clearLogOnClick() {
        existingDefectList.getItems().clear();
    }

}