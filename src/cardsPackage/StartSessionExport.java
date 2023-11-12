package cardsPackage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mainlinePackage.MainlineGUIHandler;

public class StartSessionExport {

    private FXMLLoader sceneLoader;

    public StartSessionExport() {
        sceneLoader = new FXMLLoader(getClass().getResource("StartSessionUI.fxml"));
        StartSessionController controller = new StartSessionController();
        controller.setMainlineGUIHandler(MainlineGUIHandler.getInstance());
        sceneLoader.setController(controller);
    }

    public Parent getScene() throws IOException {
        return sceneLoader.load();
    }
}
