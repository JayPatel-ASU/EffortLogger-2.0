package mainlinePackage;

import cardsPackage.*;
import consolePackage.*;
import dataPackage.*;
import defectPackage.*;
import effortPackage.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import privacyPackage.PrivacyPane;

import java.io.IOException;

/**
 * @author Raeed Azom, Jay Patel
 * Main class for implementing the EffortLogger prototype.
 */
public class MainlineGUIHandler extends Application {
	TabPane tabPane;
	private static MainlineGUIHandler instance;
	public MainlineGUIHandler() {
		instance = this;
	}
	public static MainlineGUIHandler getInstance(){
		return instance;
	}

	/**
	 * Main start function, sets up all tabs and runs them
	 */
	@Override
	public void start(Stage mainStage) throws Exception {

		// Initialize data handlers
		Data Data = new Data();
		LogManager logManager = new LogManager(Data);

		tabPane = new TabPane();
		// Initialization of each prototype scene, they are then loaded onto tabs
		EffortConsoleExport ecExp = new EffortConsoleExport();
		StartSessionExport ssExp = new StartSessionExport(); // start session
		//EffortLoggerExport elExp = new EffortLoggerExport();
		DefectLoggerExport dlExp = new DefectLoggerExport();

		// Start session scenes for each piece of functionality
		Parent effortConsole = ecExp.getScene();
		Parent startSession = ssExp.getScene();
		Parent empPrivacy = new PrivacyPane();
		//Parent effortLogger = elExp.getScene();
		Parent defectLogger = dlExp.getScene();

		// Initialize each tab
		Tab tab0 = new Tab("Effort Console", effortConsole);
		Tab tab1 = new Tab("DefectLogger", defectLogger);
		Tab tab2 = new Tab("Planning Poker", startSession);
		//Tab tab3 = new Tab("Employee Privacy", empPrivacy);
		//Tab tab3 = new Tab("EffortLogger", effortLogger);

		tabPane.getTabs().addAll(tab0, tab1, tab2);

		// Setting up formatting through spaces and anchorpane
		AnchorPane pane = new AnchorPane();
		AnchorPane.setTopAnchor(tabPane, 15.0);
		AnchorPane.setBottomAnchor(tabPane, 15.0);
		AnchorPane.setRightAnchor(tabPane, 15.0);
		AnchorPane.setLeftAnchor(tabPane, 15.0);
		pane.getChildren().addAll(tabPane);

		// Show scene
		Scene scene = new Scene(pane, 830, 750);
		mainStage.setScene(scene);
		mainStage.show();
	}

	public void switchToPlanningPoker(Session session) throws IOException {
		Tab startSessionTab = tabPane.getTabs().get(2);
		tabPane.getTabs().remove(startSessionTab); // Remove start session tab

		// Creating the new planning poker tab
		PlanningPokerExport ppExp = new PlanningPokerExport(session);
		Parent planningPoker = ppExp.getScene();
		Tab planningPokerTab = new Tab("Planning Poker", planningPoker);
		tabPane.getTabs().add(2, planningPokerTab);
		tabPane.getSelectionModel().select(planningPokerTab); // Switch to the Planning Poker tab
	}

	public static void main(String[] args) {
		launch(args);
	}

}
