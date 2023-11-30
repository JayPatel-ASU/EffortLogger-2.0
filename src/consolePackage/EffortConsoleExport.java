package consolePackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

/**
 * @author Jay Patel
 * Export class for Effort Console
 */
public class EffortConsoleExport {
    private FXMLLoader sceneLoader;

    public EffortConsoleExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("EffortConsoleController.fxml"));
    }

    public Parent getScene() throws IOException {
        return sceneLoader.load();
    }
}
