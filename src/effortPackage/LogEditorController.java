package effortPackage;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LogEditorController {

    /** Class Instance Variables **/
    private LogEditorTestData userData;
    @FXML
    private ListView<String> outputList;

    @FXML
    private TextField DateText;

    @FXML
    private TextField TimeText;
    @FXML
    private TextField StartTimeText;
    @FXML
    private TextField StopTimeText;
    @FXML
    private TextField DeltaTimeText;

    @FXML
    protected void initialize() {
        userData = new LogEditorTestData();
        outputList.getItems().add("");
        outputList.getItems().clear();
    }

    @FXML
    protected void onStoreDataClick() {
        userData.setDate(DateText.getText());
        userData.setTime(TimeText.getText());
        userData.setStartTime(StartTimeText.getText());
        userData.setStopTime(StopTimeText.getText());
        userData.setDeltaTime(DeltaTimeText.getText());

        String outputString = userData.getDate() + "," + userData.getTime() + "," + userData.getStartTime() + "," + userData.getStopTime() + "," + userData.getDeltaTime();
        outputList.getItems().add(outputString);
    }
}