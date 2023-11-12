package cardsPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mainlinePackage.MainlineGUIHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartSessionController {

    private MainlineGUIHandler mainlineGUIHandler;

    @FXML
    private TextField sessionNameField;
    @FXML
    private Button startSessionButton;

    @FXML
    private Label errorMessageLabel;

    public void setMainlineGUIHandler(MainlineGUIHandler mainlineGUIHandler){
        this.mainlineGUIHandler = mainlineGUIHandler;
    }

    @FXML
    public void initialize() {

        // Initially there is no error message
        errorMessageLabel.setText("");

        // Disable the start button until both fields are valid
        startSessionButton.disableProperty().bind(
                sessionNameField.textProperty().isEmpty()
        );
    }

    @FXML
    protected void onStartSession() throws IOException {
        // Check if there is a name
        if (sessionNameField.getText().isEmpty()) {
            errorMessageLabel.setText("Please enter a session name.");
            return;
        }
        // If both inputs are valid, proceed with starting the session
        try {
            startSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startSession() throws IOException {
        // Deck of cards
        List<Card> cardDeck = new ArrayList<>(Arrays.asList(
                new Card(0.0),
                new Card(0.5),
                new Card(1),
                new Card(2),
                new Card(3),
                new Card(5),
                new Card(8),
                new Card(13),
                new Card(20),
                new Card(40),
                new Card(100),
                new Card(-1) //"?"
        ));

        // Switch to the main planning poker page
        errorMessageLabel.setText("");
        List<User> participants = generateParticipants(8);

        Session session = new Session(sessionNameField.getText(), participants.get(0), cardDeck);
        mainlineGUIHandler.switchToPlanningPoker(session);
    }

    private List<User> generateParticipants(int participantCount){
        List<User> participants = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < 8) {
                String userId = String.valueOf(i + 1);
                String userName = "User " + String.valueOf(i + 1);
                User user = new User(userId, userName, User.Role.PARTICIPANT);
                participants.add(user);
                System.out.println(userName);
            } else {
                participants.add(null);
            }
        }
        return participants;
    }


}
