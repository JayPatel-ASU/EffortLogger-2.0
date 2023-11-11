package cardsPackage;


public class User {

    // Enum definitions
    public enum Role {
        HOST, PARTICIPANT
    }

    // Based off of card selection status
    public enum Status {
        WAITING, SELECTED, CONFIRMED
    }

    // Attributes
    private String userId;
    private String userName;
    private Card selectedCard;
    private Role role;
    private Status status;

    // Constructor
    public User(String userId, String userName, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.selectedCard = null; // Initially no card is selected
        status = Status.WAITING;
    }

    // *********** METHODS ************************************

    public void selectCard(Card card) {
    	// If selection is already confirmed, cant change selection
    	if (status != Status.CONFIRMED) {
    		this.selectedCard = card; // Sets the user's selection to a card
    		status = Status.SELECTED; // Change status of user accordingly
    	}
    }

    public void confirmCard() {
    	if (status == Status.SELECTED) {
    		status = Status.CONFIRMED;
    	}
    }

    public void reset() {
    	selectedCard = null;
    	status = Status.WAITING;
    }


    // ************** GETTERS AND SETTERS *************************
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Card getSelectedCard() {
    	return selectedCard;
    }

    public void setSelectedCard(Card card) {
    	this.selectedCard = card;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}