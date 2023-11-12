package cardsPackage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cardsPackage.User.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CardsMain extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CardMechanismUI.fxml"));

			// Create the controller instance and set session
            PlanningPokerController controller = new PlanningPokerController();
            loader.setController(controller);
            //controller.setSession(initSession());  // Pass session to controller



            Parent root = loader.load();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//controller.initialize();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static Session initSession() {
		// ******** CREATING OBJECTS **********************
		// List of Users
		User user1 = new User("1", "CharlieM", Role.HOST);
		/*
		User user2 = new User("2", "GraceT", Role.PARTICIPANT);
		User user3 = new User("3", "BradQ", Role.PARTICIPANT);
		User user4 = new User("4", "LucyP", Role.PARTICIPANT);
		User user5 = new User("5", "AaronJ", Role.PARTICIPANT);
		User user6 = new User("6", "GregP", Role.PARTICIPANT);
		User user7 = new User("7", "AdamR", Role.PARTICIPANT);
		User user8 = new User("8", "ChrisL", Role.PARTICIPANT);
		*/
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
		UserStory story1 = new UserStory(
				"Creating backend",
				"Java, DataBase, UML");
		UserStory story2 = new UserStory(
				"Implementing JavaFX",
				"JavaFX, UI");
		UserStory story3 = new UserStory(
				"Merging Prototypes",
				"Merge, Compatability");

		// ****** SESSION CREATION ***************************

		//New session with user1 as the host and using cardDeck as each user's card deck
		Session session = new Session("Room1", user1, cardDeck);

		/*
		// Adding other users to the session
		session.addParticipant(user2);
		session.addParticipant(user3);
		session.addParticipant(user4);
		session.addParticipant(user5);
		session.addParticipant(user6);
		session.addParticipant(user7);
		session.addParticipant(user8);
		*/

		// Adding topics to the queue
		session.addStory(story1);
		session.addStory(story2);
		session.addStory(story3);
		//session.popTopic();
		return session;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
