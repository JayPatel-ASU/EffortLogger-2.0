package editorPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jay Patel
 * Main function for Log Editor UI
 */
public class LogEditor extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        LogEditorExport le = new LogEditorExport();
        Scene scene = new Scene(le.getScene(), 620, 500);
        stage.setTitle("LogEditor");
        stage.setScene(scene);
        stage.show();
    }
}
