package effortPackage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class EffortLoggerExport {
	private FXMLLoader sceneLoader;
	
	public EffortLoggerExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("LogEditorController.fxml"));
	}

	public Parent getScene() throws IOException {
		return sceneLoader.load();
	}
}