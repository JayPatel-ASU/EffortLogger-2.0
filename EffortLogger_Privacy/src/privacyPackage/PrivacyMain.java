package privacyPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Raeed Azom
 * The implementation class for the PrivacyPane class, puts the pane into a scene and runs it for testing.
 */
public class PrivacyMain extends Application {

	@Override
	public void start(Stage mainStage) throws Exception {
		// Basic stage and scene setup and running
		PrivacyPane mainPane = new PrivacyPane();
		Scene mainScene = new Scene(mainPane, 1500, 750);
		mainStage.setScene(mainScene);
		mainStage.show();
	}

	public static void main(String[] args) {
		// Runs GUI application
		launch(args);
	}
}