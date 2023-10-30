package cardsPackage;


public class UserStory {

	// *********** ATTRIBUTES *************
	private String title;
	private String description;

	// Constructor
	public UserStory(String title, String description) {
		this.title = title;
		this.description = description;
	}

	// ****** GETTERS AND SETTERS ***********
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
