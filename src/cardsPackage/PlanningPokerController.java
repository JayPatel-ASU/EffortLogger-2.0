package cardsPackage;

import dataPackage.*;

import java.io.IOException;
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

	Data data;
	LogManager logManager;

	// ****************************
	// Session
	private Session session;

	private double average;
	private double median;
	private double highestFrequency;
	private String title;


	public void setSession(Session session) {
		this.session = session;
	}

	// ************* UI ELEMENTS ******************************
	// Card deck
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
	private Label selectedCard; // Displays selected card value
	@FXML
	private Button confirmButton; // Confirms/locks in the users selection
	@FXML
	private Label currentUserLabel; // Current user that is selecting a value


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
	private Label userVoteLabel1;
	@FXML
	private Label userNameLabel1;
	@FXML
	private Label userNameLabel2;
	@FXML
	private Label userNameLabel3;
	@FXML
	private Label userNameLabel4;
	@FXML
	private Label userNameLabel5;
	@FXML
	private Label userNameLabel6;
	@FXML
	private Label userNameLabel7;
	@FXML
	private Label userNameLabel8;
	@FXML
	private Label userNameLabel9;
	@FXML
	private Label userNameLabel10;
	@FXML
	private Label userNameLabel11;
	@FXML
	private Label userNameLabel12;

	// Current Topic
	@FXML
	private Label titleLabel;


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
			userVoteLabel1.setText(Double.toString(session.getHost().getSelectedCard().getValue()));
		} else {
			userVoteLabel1.setText("...");
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
			System.out.println();
			if (user.getStatus() != Status.CONFIRMED) {
				return false;
			}
		}
		return true;
	}

	// Update names of participants
	@FXML
	protected void updateNames() {
		// Create an array or list of all your labels for easier access
		Label[] labels = new Label[] {
				userNameLabel1, userNameLabel2, userNameLabel3, userNameLabel4,
				userNameLabel5, userNameLabel6, userNameLabel7, userNameLabel8
		};

		// Iterate through the list of participants and update each label
		List<User> participants = session.getParticipants();
		for (int i = 0; i < labels.length; i++) {
				labels[i].setText("User " + String.valueOf(i + 1));
		}
		if (session.getCurrentUser()!= null)
			currentUserLabel.setText(session.getCurrentUser().getUserName());

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
			average = session.calculateAverage(results);
			median = session.calculateMedian(results);
			highestFrequency = session.mostCommonValue(results);
			averageLabel.setText("Average: " + String.valueOf(average));
			medianLabel.setText("Median: " + String.valueOf(median));
			highestLabel.setText("Highest Frequency: " + String.valueOf(highestFrequency));
			resultsLabel.setText("Results:");
			session.setSessionState(SessionStatus.COMPLETED);
		} else {
			averageLabel.setText("Average: ...");
			medianLabel.setText("Median: ...");
			highestLabel.setText("Highest Frequency: ...");
			resultsLabel.setText("Waiting for Results...");
		}
		//session.setSessionState(SessionStatus.COMPLETED);
	}

	// ********** POST RESULTS BUTTONS ***********

	// Next topic
	@FXML
	protected void action1() {
		if (session.getSessionState() == SessionStatus.COMPLETED) {
			session.reset();
			session.popTopic();
			Update();

			//data.storeEstimation(session.getSessionId(), title, average, median, highestFrequency);
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
	public void initialize() throws IOException{
		data = new Data();
		logManager = new LogManager(data);
		ArrayList <String> topics = data.getDefinitions(0);

		for (int i = 0; i < topics.size(); i++){
			//[Whatever the name of ur topics list is].get().add(topic.get(i));
			UserStory story = new UserStory(topics.get(i), "");
			session.addStory(story);
		}

		if(session != null) {
			session.setSessionState(SessionStatus.WAITING);
			session.popTopic(); // Set current topic from queue
			updateSelection();
			updateConfirmedSelection();
			updateVotingLabel();
			//updateResults();
			//userNameLabel1.setText(session.getHost().getUserName());
			userNameLabel1.setText(session.getParticipants().get(0).getUserName());
			updateNames();


			// Setting current story
			if (session.getCurrentStory() != null) {
				titleLabel.setText(session.getCurrentStory().getTitle());
			}
		}
	}

	public void Update() {
		if(session != null) {
			// Setting current story
			if (session.getCurrentStory() != null) {
				titleLabel.setText(session.getCurrentStory().getTitle());
			}
			updateSelection();
			updateConfirmedSelection();
			updateVotingLabel();
			updateNames();
			//updateResults();


		}
	}
}
