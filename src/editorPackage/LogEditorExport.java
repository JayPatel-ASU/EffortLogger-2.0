package editorPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;


public class LogEditorExport {
    private FXMLLoader sceneLoader;

    public LogEditorExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("LogEditorController.fxml"));
    }

    public Parent getScene() throws IOException {
        return sceneLoader.load();
    }
}
