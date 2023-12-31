package mainlinePackage;

import cardsPackage.*;
import consolePackage.*;

import defectPackage.*;
import editorPackage.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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


		// Initialize tabPane + properties
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		// Initialization of each prototype scene, they are then loaded onto tabs
		EffortConsoleExport ecExp = new EffortConsoleExport();
		StartSessionExport ssExp = new StartSessionExport();
		DefectLoggerExport dlExp = new DefectLoggerExport();
		LogEditorExport leExp = new LogEditorExport();

		// Start session scenes for each piece of functionality
		Parent effortConsole = ecExp.getScene();
		Parent startSession = ssExp.getScene();
		Parent defectLogger = dlExp.getScene();
		Parent LogEditor = leExp.getScene();

		// Initialize each tab
		Tab tab0 = new Tab("Effort Console", effortConsole);
		Tab tab1 = new Tab("DefectLogger", defectLogger);
		Tab tab2 = new Tab("Log Editor", LogEditor);
		Tab tab3 = new Tab("Planning Poker", startSession);

		tabPane.getTabs().addAll(tab0, tab1, tab2, tab3);

		// Setting up formatting through spaces and anchorpane
		AnchorPane pane = new AnchorPane();
		AnchorPane.setTopAnchor(tabPane, 15.0);
		AnchorPane.setBottomAnchor(tabPane, 15.0);
		AnchorPane.setRightAnchor(tabPane, 15.0);
		AnchorPane.setLeftAnchor(tabPane, 15.0);
		pane.getChildren().addAll(tabPane);

		// Various setup options for stage
		Scene scene = new Scene(pane, 830, 750);
		mainStage.setScene(scene);
		mainStage.setTitle("EffortLogger");
		mainStage.setResizable(false);
		mainStage.show();
	}

	public void switchToPlanningPoker(Session session) throws IOException {
		Tab startSessionTab = tabPane.getTabs().get(3);
		tabPane.getTabs().remove(startSessionTab); // Remove start session tab

		// Creating the new planning poker tab
		PlanningPokerExport ppExp = new PlanningPokerExport(session);
		Parent planningPoker = ppExp.getScene();
		Tab planningPokerTab = new Tab("Planning Poker", planningPoker);
		tabPane.getTabs().add(3, planningPokerTab);
		tabPane.getSelectionModel().select(planningPokerTab); // Switch to the Planning Poker tab
	}

	public static void main(String[] args) {
		launch(args);
	}

}
