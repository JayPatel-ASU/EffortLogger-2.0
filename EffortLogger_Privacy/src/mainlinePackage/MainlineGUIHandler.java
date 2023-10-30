package mainlinePackage;

import cardsPackage.PlanningPokerExport;
import defectPackage.DefectLoggerExport;
import effortPackage.EffortLoggerExport;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import privacyPackage.PrivacyPane;

/**
 * @author Raeed Azom
 * Main class for implementing the EffortLogger prototype.
 */
public class MainlineGUIHandler extends Application {
	TabPane tabPane;

	/**
	 * Main start function, sets up all tabs and runs them
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		tabPane = new TabPane();
		// Initialization of each prototype scene, they are then loaded onto tabs
		PlanningPokerExport ppExp = new PlanningPokerExport();
		EffortLoggerExport elExp = new EffortLoggerExport();
		DefectLoggerExport dlExp = new DefectLoggerExport();
		Parent planningPoker = ppExp.getScene();
		Parent empPrivacy = new PrivacyPane();
		Parent effortLogger = elExp.getScene();
		Parent defectLogger = dlExp.getScene();
		Tab tab1 = new Tab("Planning Poker", planningPoker);
		Tab tab2 = new Tab("Employee Privacy", empPrivacy);
		Tab tab3 = new Tab("EffortLogger", effortLogger);
		Tab tab4 = new Tab("DefectLogger", defectLogger);
		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
		// Setting up formatting through spaces and anchorpane
		AnchorPane pane = new AnchorPane();
		AnchorPane.setTopAnchor(tabPane, 15.0);
		AnchorPane.setBottomAnchor(tabPane, 15.0);
		AnchorPane.setRightAnchor(tabPane, 15.0);
		AnchorPane.setLeftAnchor(tabPane, 15.0);
		pane.getChildren().addAll(tabPane);
		// Show scene
		Scene scene = new Scene(pane, 1500, 750);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
