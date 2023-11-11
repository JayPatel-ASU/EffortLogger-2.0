package privacyPackage;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Raeed Azom
 * Main pane class for the privacy prototype
 * Used to initialize an instance of the pane
 */
public class PrivacyPane extends HBox {
	private ManagerView managerView;
	private TextField[] inputData;
	private TextField[] texts;
	private CheckBox[] boxes;
	private Button sendData;
	private Button clearData;

	/**
	 * Constructor, contains all the functionality for the class
	 */
	public PrivacyPane() {
		// Initializng needed variables and storage
		managerView = new ManagerView();
        boxes = new CheckBox[3];
        texts = new TextField[2];
		// Creation of the leftmost box - userBox, which contanis inputs and user settings.
		VBox userBox = new VBox();
		Text textTitle = new Text("DataPackage.Data Entry");
        textTitle.setStyle("-fx-font-size: 18px;");
        // Saves input textfields to a array for later use
		inputData = new TextField[9];
		for(int i = 0; i < inputData.length; i++) {
			inputData[i] = new TextField();
		}
		GridPane userData = new GridPane();
		// Sets up input tables
		userData.add(textTitle, 0, 0);
		userData.add(new Label("Name"), 0, 1);
		userData.add(inputData[0], 1, 1);
		userData.add(new Label("Role"), 0, 2);
		userData.add(inputData[1], 1, 2);
		userData.add(new Label("Date"), 0, 3);
		userData.add(inputData[2], 1, 3);
		userData.add(new Label("Start"), 0, 4);
		userData.add(inputData[3], 1, 4);
		userData.add(new Label("Stop"), 0, 5);
		userData.add(inputData[4], 1, 5);
		userData.add(new Label("Delta Time"), 0, 6);
		userData.add(inputData[5], 1, 6);
		userData.add(new Label("Life Cycle Step"), 0, 7);
		userData.add(inputData[6], 1, 7);
		userData.add(new Label("Effort Category"), 0, 8);
		userData.add(inputData[7], 1, 8);
		// Wraps the deliverables because the the title is long
		Label deliverableLabel = new Label("Deliverable / Interruption / etc.");
		deliverableLabel.setWrapText(true);
		userData.add(deliverableLabel, 0, 9);
		userData.add(inputData[8], 1, 9);
		userBox.getChildren().add(userData);
		// User config section, for settings
		VBox userConfig = new VBox();
		textTitle = new Text("Privacy Settings");
        textTitle.setStyle("-fx-font-size: 18px;");
		userConfig.getChildren().add(textTitle);
		boxes[0] = new CheckBox("Hide Name");
		userConfig.getChildren().add(boxes[0]);
		userConfig.getChildren().add(new Label("More Settings To Be Implemented Later"));
		userBox.getChildren().add(userConfig);

		// Debug box, which is used for sending data and is located in the middle
		VBox debugBox = new VBox();
		VBox debugSettings = new VBox();
		textTitle = new Text("Manager Settings");
        textTitle.setStyle("-fx-font-size: 18px;");
		debugSettings.getChildren().add(textTitle);
		CheckBox viewNames = new CheckBox("View Names");
		viewNames.setSelected(true);
		viewNames.selectedProperty().addListener(ListenerGenerator.viewNamesListener(managerView));
		debugSettings.getChildren().add(viewNames);
		texts[0] = new TextField("1");
		texts[0].textProperty().addListener(ListenerGenerator.numberTextListener(texts[0]));
		HBox temp = new HBox(new Label("DataPackage.Data Viewing Threshold: "), texts[0]);
		debugSettings.getChildren().add(temp);
		debugSettings.getChildren().add(new Label("More Settings To Be Implemented Later"));
		debugBox.getChildren().add(debugSettings);
		// DataPackage.Data manipulation buttons, which allow you to save and clear data using listeners
		sendData = new Button("Send DataPackage.Data");
		sendData.setOnAction(ListenerGenerator.addDataListener(managerView, boxes, texts, inputData));
		debugBox.getChildren().add(sendData);
		clearData = new Button("Clear DataPackage.Data");
		clearData.setOnAction(ListenerGenerator.clearDataListener(managerView));
		debugBox.getChildren().add(clearData);
		// Debug config, which lets the user customize batch generation options
		VBox debugConfig = new VBox();
		textTitle = new Text("Batch Configuration");
        textTitle.setStyle("-fx-font-size: 18px;");
		debugConfig.getChildren().add(textTitle);
		boxes[1] = new CheckBox("Enable Batch Sending");
		debugConfig.getChildren().add(boxes[1]);
		texts[1] = new TextField("10");
		texts[1].textProperty().addListener(ListenerGenerator.numberTextListener(texts[1]));
		temp = new HBox(new Label("Batch Size: "), texts[1]);
		debugConfig.getChildren().add(temp);
		boxes[2] = new CheckBox("Randomize DataPackage.Data");
		debugConfig.getChildren().add(boxes[2]);
		debugBox.getChildren().add(debugConfig);

		// Manager section, implements the managerview and is located on the right
		VBox managerBox = new VBox();
		managerBox.getChildren().add(managerView);

		// Setting spacing for the user area
		userData.setVgap(4);
		userConfig.setSpacing(4);
		userBox.setSpacing(15);
		this.getChildren().add(userBox);
		// Setting spacing for the debug area
		debugSettings.setSpacing(4);
		debugConfig.setSpacing(4);
		debugBox.setSpacing(15);
		this.getChildren().add(debugBox);
		// Setting spacing for the manager area
		managerBox.setSpacing(15);
		this.getChildren().add(managerBox);
		// Setting overall spacing
		this.setSpacing(30);
	}
}