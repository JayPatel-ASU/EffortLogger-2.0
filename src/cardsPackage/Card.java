package cardsPackage;


public class Card {

	// Attributes
	private double value;
	private boolean isSelected;

	// Constructor
	public Card(double value) {
		this.value = value;
		isSelected = false;
	}

	// *************** Methods ****************
	public void select() {
		isSelected = true;
	}

	public void deselect() {
		isSelected = false;
	}

	// ***** Getters and Setters *****************
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

}
