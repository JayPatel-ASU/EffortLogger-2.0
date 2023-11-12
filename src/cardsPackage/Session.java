package cardsPackage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Session {

	//Enum definitions
	public enum SessionStatus {
		WAITING, REVEALING, COMPLETED
	}

	// Attributes
	private String sessionId;
	private UserStory currentTopic; // Topic that participants will be voting on
	private List<User> participants; // List of participants in the session
	private User host; // User that created the session. Might have access to session settings and such
	private User currentUser; // current user that is voting
	private SessionStatus sessionState; // Players are voting, cards are being revealed, reveal/discussion is complete, etc.
	private Timer currentTime; // Timer could be used for voting, discussion time, etc.
	private List<UserStory> storyQueue; // List of topics that will be addressed during the session
	private List<Card> cardDeck; // Deck of estimations that all users will be choosing from


	// Constructor
	public Session(String sessionId, User host, List<Card> cardDeck) {
		this.sessionId = sessionId;
		this.host = host;
		this.sessionState = SessionStatus.WAITING; // set to WAITING initially

		// Initialize lists
		this.participants = new ArrayList<>();
		this.storyQueue = new ArrayList<>();
		this.cardDeck = cardDeck;
		addParticipant(host);
		setCurrentUser(host);
	}

	public void Update() {
		if (allUsersConfirmed()) {
			sessionState = SessionStatus.REVEALING;
		}
	}

	// ************* METHODS *********************
	public void startSession() {

	}

	public void endSession() {

	}

	public List<Card> revealCards() {
		List<Card> cards = new ArrayList<>();

		for (User participant : participants) {
			Card card = participant.getSelectedCard();
			if (card != null) {
				cards.add(card);
			}
		}
		return cards;
	}

	public void addStory(UserStory userStory) {
		storyQueue.add(userStory);
	}

	// Pop the first topic from the queue
	public void popTopic() {
		if (!storyQueue.isEmpty()) {
			currentTopic = storyQueue.remove(0);
		}
	}

	// Resets all users selections and sets status to WAITING
	public void reset() {
		for (User participant : participants) {
			participant.reset();
		}
		sessionState = SessionStatus.WAITING;
	}

	public boolean allUsersConfirmed() {
		for (User participant : participants) {  // assuming you have a list named participants
	        if (participant.getStatus() != User.Status.CONFIRMED) {
	            return false;
	        }
	    }
	    return true;
	}

	public void updateCurrentUser() {
		if (currentUser.getStatus() == User.Status.CONFIRMED){
			currentUser = participants.get(participants.indexOf(currentUser) + 1);
		}
	}

	public void addParticipant(User user) {
		participants.add(user);
	}

	// Returns the most commonly chosen card value
	public double mostCommonValue(List<Card> cards) {
		// Check if the list is null or empty
		if (cards == null || cards.isEmpty()) {
			return 0;
		}

		// Map to store the frequency of each card value
		// Key: Card value, Value: Number of occurances
		Map<Double, Integer> frequencyMap = new HashMap<>();

		    // Iterate over each card in cards, populate the map
		    for (Card card : cards) {
		    	// For each card's value, if it's not in the map, it defaults to 0,
		        // and then we add 1 to the count.
		        frequencyMap.put(card.getValue(), frequencyMap.getOrDefault(card.getValue(), 0) + 1);
		    }

		    // Find the most common value
		    double mostCommonValue = cards.get(0).getValue(); // Assume first card has highest frequency
		    int maxFrequency = 1; // Since the list isn't empty, there's at least one card

		    // Check each key-value pair in the map
		    for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
		    	// If card appears more than current highest frequency card:
		    	// Update mostCommonValue and maxFrequency accordingly
		        if (entry.getValue() > maxFrequency) {
		            maxFrequency = entry.getValue();
		            mostCommonValue = entry.getKey();
		        }
		    }

		    return mostCommonValue; // Highest frequency card
	}

	// Returns the average of all chosen card values
	public double calculateAverage(List<Card> cards) {
		if (cards == null || cards.isEmpty()) {
			return 0;
		}

		double sum = 0;
		int count = 0;

		for (Card card : cards) {
			sum += card.getValue();
			count++;
		}

		if (count == 0) {
			return 0;
		}

		return sum/count;
	}

	// Returns the median of all chosen card values
	public double calculateMedian(List<Card> cards) {
		if (cards == null || cards.isEmpty()) {
			return 0;
		}

		List<Double> values = cards.stream()
								.map(Card::getValue)
								.sorted()
								.collect(Collectors.toList());
		int size = values.size();
		if (size % 2 == 1) {
			return values.get(size / 2);
		} else {
			return (values.get((size - 1) / 2) + values.get(size / 2)) / 2.0;
		}
	}

	// Remove a topic from the queue
	public void removeFromQueue(UserStory userStory) {
		if (storyQueue != null && userStory != null) {
			storyQueue.remove(userStory);
		}
	}

	// ************* Getters and Setters **************************
	public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UserStory getCurrentStory() {
        return currentTopic;
    }

    public void setCurrentStory(UserStory currentTopic) {
        this.currentTopic = currentTopic;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser(User currentUser) {this.currentUser = currentUser; }

    public SessionStatus getSessionState() {
        return sessionState;
    }

    public void setSessionState(SessionStatus sessionState) {
        this.sessionState = sessionState;
    }

    public Timer getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timer currentTime) {
        this.currentTime = currentTime;
    }

    public List<UserStory> getStoryQueue() {
        return storyQueue;
    }

    public void setStoryQueue(List<UserStory> storyQueue) {
        this.storyQueue = storyQueue;
    }

    public List<Card> getCardDeck() {
    	return cardDeck;
    }

    public void setCardDeck(List<Card> cardDeck) {
    	this.cardDeck = cardDeck;
    }
}
