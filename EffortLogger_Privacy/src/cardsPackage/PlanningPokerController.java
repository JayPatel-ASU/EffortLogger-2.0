package cardsPackage;


import java.util.ArrayList;
import java.util.List;

import cardsPackage.Session.SessionStatus;
import cardsPackage.User.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class PlanningPokerController {

	// ****************************
	// Session
	private Session session;


	public void setSession(Session session) {
		this.session = session;
	}

	// ************* UI ELEMENTS ******************************

	// General Stuff
	@FXML
	private VBox VBox; // The window the application is in
	@FXML
	private AnchorPane anchorPane; // Child of VBox, parent to everything else except menu items
	@FXML
	private MenuBar menuBar; // Menu at top of app
	@FXML
	private Menu menuButton1; // "File"
	@FXML
	private Menu menuButton2; // "Edit"
	@FXML
	private Menu menuButton3; // "Help"

	// Card deck
	@FXML
	private GridPane cardGrid; // holds the card deck buttons
	@FXML
	private Button cardButton1; // Buttons are labeled with their value. Ex: "0", "1/2", "13", etc.
	@FXML
	private Button cardButton2;
	@FXML
	private Button cardButton3;
	@FXML
	private Button cardButton4;
	@FXML
	private Button cardButton5;
	@FXML
	private Button cardButton6;
	@FXML
	private Button cardButton7;
	@FXML
	private Button cardButton8;
	@FXML
	private Button cardButton9;
	@FXML
	private Button cardButton10;
	@FXML
	private Button cardButton11;
	@FXML
	private Button cardButton12;

	// Card Selection confirmation
	@FXML
	private VBox confirmVBox; // Holds the selected card and confirm button
	@FXML
	private Label selectedCard; // Displays selected card value
	@FXML
	private Button confirmButton; // Confirms/locks in the users selection


	// Topics Queue
	@FXML
	private VBox topicsQueueVBox; // Holds all the elements of the queue
	@FXML
	private ScrollPane topicsScrollPane; // Holds the list of topics in the queue
	@FXML
	private AnchorPane topicsAnchorPane; // child within the scroll pane to hold topics
	@FXML
	private HBox topicsQueueTemplate; // Template for each topic, topics contain a label and button
	@FXML
	private Button topicsQueueButton; // Button for removing topic from queue
	@FXML
	private Label topicsQueueLabel; // Title of the topic displayed in queue

	// Voting Box
	@FXML
	private BorderPane votingBorderPane;
	@FXML
	private HBox votingHBox;
	@FXML
	private Label messageVotingLabel;
	@FXML
	private Label votingTimerLabel;
	@FXML
	private GridPane votingGridPane;
	@FXML
	private VBox userVoteVBox;
	@FXML
	private Label userVoteLabel;
	@FXML
	private Label userNameLabel;

	// Current Topic
	@FXML
	private VBox titleVBox;
	@FXML
	private Label titleLabel;
	@FXML
	private Label descriptionLabel;

	// Results
	@FXML
	private Label resultsLabel;
	@FXML
	private Label averageLabel;
	@FXML
	private Label medianLabel;
	@FXML
	private Label highestLabel;
	@FXML
	private Button revealAction1;

	// ****** METHODS *********

	@FXML
	protected void onCardSelect1() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(0);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect2() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(1);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect3() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(2);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect4() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(3);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect5() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(4);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect6() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(5);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect7() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(6);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect8() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(7);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect9() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(8);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect10() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(9);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect11() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(10);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}
	@FXML
	protected void onCardSelect12() {
		if(session.getHost().getStatus() != (Status.CONFIRMED)) {
			Card selectedCard = session.getCardDeck().get(11);
			session.getHost().setSelectedCard(selectedCard);
			updateSelection();
		}
	}

	// Updates the number in the confirmation box
	@FXML
	protected void updateSelection() {
		if (session.getHost().getStatus() != Status.CONFIRMED) {
			session.getHost().setStatus(Status.SELECTED);
			if(session.getHost().getSelectedCard() != null) {
				selectedCard.setText(Double.toString(session.getHost().getSelectedCard().getValue()));
			} else {
				selectedCard.setText("...");
			}
		}
	}

	// Updates the user's selection in the user table
	@FXML
	protected void updateConfirmedSelection() {
		if(session.getHost().getStatus() == Status.CONFIRMED) {
			userVoteLabel.setText(Double.toString(session.getHost().getSelectedCard().getValue()));
		} else {
			userVoteLabel.setText("...");
		}
	}

	// Confirms the user's choice
	@FXML
	protected void onConfirmClick() {
		if(session.getHost().getSelectedCard() != null) {
			session.getHost().confirmCard();
		}
		updateConfirmedSelection();
		updateVotingLabel();
	}

	// Checks if all users in the room are confirmed
	@FXML
	protected boolean checkIfAllConfirmed() {
		for (User user : session.getParticipants()) {
			if (user.getStatus() != Status.CONFIRMED) {
				return false;
			}
		}
		return true;
	}

	// Update voting message
	@FXML
	protected void updateVotingLabel() {
		if (checkIfAllConfirmed()) {
			session.setSessionState(SessionStatus.REVEALING);
			messageVotingLabel.setText("Revealing Cards");
			updateResults();
		} else {
			messageVotingLabel.setText("Waiting for selection...");
		}
	}

	// Update Results section
	@FXML
	protected void updateResults() {
		List<Card> results = new ArrayList<>();
		if (session.getSessionState() == SessionStatus.REVEALING) {
			for (User participant : session.getParticipants()) {
				results.add(participant.getSelectedCard());
			}
			averageLabel.setText("Average: " + session.calculateAverage(results));
			medianLabel.setText("Median: " + session.calculateMedian(results));
			highestLabel.setText("Highest Frequency: " + session.mostCommonValue(results));
			resultsLabel.setText("Results:");
		} else {
			averageLabel.setText("Average: ...");
			medianLabel.setText("Median: ...");
			highestLabel.setText("Highest Frequency: ...");
			resultsLabel.setText("Waiting for Results...");
		}
		session.setSessionState(SessionStatus.COMPLETED);
	}

	// ********** POST RESULTS BUTTONS ***********

	// Next topic
	@FXML
	protected void action1() {
		if (session.getSessionState() == SessionStatus.COMPLETED) {
			session.reset();
			session.popTopic();
			Update();
		}
	}

	// Revote
	@FXML
	protected void action2() {
		if (session.getSessionState() == SessionStatus.COMPLETED) {
			session.reset();
			Update();
		}
	}

	// Sets up all the labels
	public void initialize() {
		if(session != null) {
			session.setSessionState(SessionStatus.WAITING);
			session.popTopic(); // Set current topic from queue
			updateSelection();
			updateConfirmedSelection();
			updateVotingLabel();
			//updateResults();
			userNameLabel.setText(session.getHost().getUserName());

			// Setting current story and description
			if (session.getCurrentStory() != null) {
				titleLabel.setText(session.getCurrentStory().getTitle());
				descriptionLabel.setText(session.getCurrentStory().getDescription());
			}
		}
	}

	public void Update() {
		if(session != null) {
			// Setting current story and description
			if (session.getCurrentStory() != null) {
				titleLabel.setText(session.getCurrentStory().getTitle());
				descriptionLabel.setText(session.getCurrentStory().getDescription());
			}
			updateSelection();
			updateConfirmedSelection();
			updateVotingLabel();
			//updateResults();
		}
	}
}
