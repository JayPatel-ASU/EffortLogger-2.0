package mainlinePackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import privacyPackage.PrivacyPane;

/**
 * @author Raeed Azom
 * Main class for implementing the EffortLogger prototype.
 */
public class MainlineGUIHandler extends Application {
	TabPane tabPane;
	
	public void start(Stage mainStage) throws Exception {
		tabPane = new TabPane();
		Tab tab1 = new Tab("Tab1", new Text("Tab1"));
		Tab tab2 = new Tab("EffortLogger", new PrivacyPane());
		Tab tab3 = new Tab("Tab3", new Text("Tab3"));
		tabPane.getTabs().addAll(tab1, tab2, tab3);
		AnchorPane pane = new AnchorPane();
		AnchorPane.setTopAnchor(tabPane, 15.0);
		AnchorPane.setBottomAnchor(tabPane, 15.0);
		AnchorPane.setRightAnchor(tabPane, 15.0);
		AnchorPane.setLeftAnchor(tabPane, 15.0);
		pane.getChildren().addAll(tabPane);
		Scene scene = new Scene(pane, 1500, 750);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
