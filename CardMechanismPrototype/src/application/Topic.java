package application;

public class Topic {
	
	// *********** ATTRIBUTES *************
	private String title;
	private String description;
	
	// Constructor
	public Topic(String title, String description) {
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
