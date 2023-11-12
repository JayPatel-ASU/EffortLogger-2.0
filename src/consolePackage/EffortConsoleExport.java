package consolePackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class EffortConsoleExport {
    private FXMLLoader sceneLoader;

    public EffortConsoleExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("EffortConsoleController.fxml"));
    }

    public Parent getScene() throws IOException {
        return sceneLoader.load();
    }
}
