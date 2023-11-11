package defectPackage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class DefectLoggerExport {
	private FXMLLoader sceneLoader;

	public DefectLoggerExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("DefectLoggerController.fxml"));
	}

	public Parent getScene() throws IOException {
		return sceneLoader.load();
	}
}
