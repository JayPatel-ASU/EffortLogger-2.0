package consolePackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jay Patel
 * Main function for defect console UI
 */
public class EffortConsole extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        EffortConsoleExport ec = new EffortConsoleExport();
        Scene scene = new Scene(ec.getScene(), 620, 500);
        stage.setTitle("EffortConsole");
        stage.setScene(scene);
        stage.show();
    }
}
