package cardsPackage;

	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import application.User.Role;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		//launch(args);
		
		// ******** CREATING OBJECTS **********************
		
		// List of Users
		User user1 = new User("1", "CharlieM", Role.HOST);
		User user2 = new User("2", "GraceT", Role.PARTICIPANT);
		User user3 = new User("3", "BradQ", Role.PARTICIPANT);
		User user4 = new User("4", "LucyP", Role.PARTICIPANT);
		User user5 = new User("5", "AaronJ", Role.PARTICIPANT);
		User user6 = new User("6", "GregP", Role.PARTICIPANT);
		User user7 = new User("7", "AdamR", Role.PARTICIPANT);
		User user8 = new User("8", "ChrisL", Role.PARTICIPANT);
		
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
		
		// Some topics
		Topic topic1 = new Topic(
				"Creating backend functionality",
				"Writing classes and their methods to test functionality"
				+"\nPurpose is to test backend code before starting javaFX stuff");
		Topic topic2 = new Topic(
				"Implementing backend code into JavaFX",
				"After successfully testing funcionality of the backend code,"
				+"\nWe will start implementing a UI for the code in JavaFX");
		Topic topic3 = new Topic(
				"Integration with team's prototypes",
				"After successfully finished the development of the individual"
				+"\nprototypes, we will collaborate and integrate all our code"
				+"\ninto one singular deliverable application");
				
		
		// ****** SESSION CREATION ***************************
		
		//New session with user1 as the host and using cardDeck as each user's card deck
		Session session = new Session("Room1", user1, cardDeck);
		
		// Adding other users to the session
		session.addParticipant(user2);
		session.addParticipant(user3);
		session.addParticipant(user4);
		session.addParticipant(user5);
		session.addParticipant(user6);
		session.addParticipant(user7);
		session.addParticipant(user8);
		
		// Adding topics to the queue
		session.addTopic(topic1);
		session.addTopic(topic2);
		session.addTopic(topic3);

		// Print participant List
		System.out.println("Participants in the session:");
		for (User participant : session.getParticipants()) {
		    System.out.println("-" + participant.getUserName());
		}
		// Print session state
		System.out.println("*** Session state: " + session.getSessionState() + " ***\n");
		
		// ***** DISPLAY TOPIC QUEUE AND CURRENT TOPIC ************
		
		session.popTopic(); // Load first topic from the queue
		System.out.println("Current topic: " + session.getCurrentTopic().getTitle());
		/*System.out.print("----- Topics Queue -----\n");
		for (Topic topic : session.getTopicsQueue()) {
			System.out.println("- " + topic.getTitle());
		}*/

		
		// ****** SIMULATED CARD SELECTION **************************
		
		//print User states (pre-selection)
		for (User participant : session.getParticipants()) {
			//System.out.println(participant.getUserName() + " " + participant.getStatus());
		}
		
		// Prompt the users to make a selection based off of the cardDeck
		//System.out.println("\n\nMake a selection\nCard Deck: ");
		for (Card card : cardDeck) {
			//System.out.print(card.getValue() + " ");
		}
		System.out.println();

		// Simulate each user selecting a card
		Random random = new Random(); // to select a random card
		
		for (User participant : session.getParticipants()) {
			Card randomCard = cardDeck.get(random.nextInt(cardDeck.size())); // random card from deck
			participant.selectCard(randomCard); // user selects the card
			//System.out.println(participant.getUserName() + " selected card with value: " + randomCard.getValue());
		}

		// **** CONFIRM CARD SELECTION ********************
	
		// Confirm all users selections (Simulating "CONFIRM" button in the UI)
		for (User participant : session.getParticipants()) {
			participant.confirmCard();
		}
		
		session.Update();
		
		// Print session state
		System.out.println("*** Session state: " + session.getSessionState() + " ***\n");
		
		// ***** PRINT CALCULATIONS BASED ON CONFIRMED SELECTIONS ******
		System.out.println("Average: " + session.calculateAverage(session.revealCards()));
		System.out.println("Median: " + session.calculateMedian(session.revealCards()));
		System.out.println("Highest frequency card: " + session.mostCommonValue(session.revealCards()));
		
		
		// ***** REPEAT ABOVE FOR THE OTHER TOPICS
		session.popTopic();
		session.reset();
		System.out.println("\nCurrent topic: " + session.getCurrentTopic().getTitle());
		for (User participant : session.getParticipants()) {
			Card randomCard = cardDeck.get(random.nextInt(cardDeck.size())); // random card from deck
			participant.selectCard(randomCard); // user selects the card
			participant.confirmCard();
		}
		session.Update();
		System.out.println("Average: " + session.calculateAverage(session.revealCards()));
		System.out.println("Median: " + session.calculateMedian(session.revealCards()));
		System.out.println("Highest frequency card: " + session.mostCommonValue(session.revealCards()));
		
		// *** REPEAT AGAIN ***
		session.popTopic();
		session.reset();
		System.out.println("\nCurrent topic: " + session.getCurrentTopic().getTitle());
		for (User participant : session.getParticipants()) {
			Card randomCard = cardDeck.get(random.nextInt(cardDeck.size())); // random card from deck
			participant.selectCard(randomCard); // user selects the card
			participant.confirmCard();
		}
		session.Update();
		System.out.println("Average: " + session.calculateAverage(session.revealCards()));
		System.out.println("Median: " + session.calculateMedian(session.revealCards()));
		System.out.println("Highest frequency card: " + session.mostCommonValue(session.revealCards()));
		
	}
	

}
