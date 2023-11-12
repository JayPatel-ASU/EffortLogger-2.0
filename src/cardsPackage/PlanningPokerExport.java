package cardsPackage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PlanningPokerExport {
	private FXMLLoader sceneLoader;

	public PlanningPokerExport(Session session) {
		sceneLoader = new FXMLLoader(getClass().getResource("CardMechanismUI.fxml"));
		PlanningPokerController controller = new PlanningPokerController();
        sceneLoader.setController(controller);
        controller.setSession(session);
	}

	public Parent getScene() throws IOException {
		return sceneLoader.load();

	}

}
