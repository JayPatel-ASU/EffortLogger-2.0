package defectPackage;


import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jay Patel
 * Main function for defect console UI
 */
public class DefectMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DefectLoggerExport exp = new DefectLoggerExport();
        Scene scene = new Scene(exp.getScene(), 620, 500);
        stage.setTitle("DefectLogger");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}