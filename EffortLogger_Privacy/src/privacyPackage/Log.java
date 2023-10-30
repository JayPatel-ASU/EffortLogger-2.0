package privacyPackage;

import javafx.scene.control.TextField;

/**
 * @author Raeed Azom
 * Log class to store the information associated with a log
 * Stores name, role, date, etc.
 */
public class Log {
	String name;
	String role;
	String date;
	String start;
	String stop;
	String deltaTime;
	String lifeCycle;
	String category;
	String etc;

	/**
	 * Basic setter constructor
	 */
	public Log(String name, String role, String date, String start, String stop, String deltaTime, String lifeCycle, String category, String etc) {
		this.name = name;
		this.role = role;
		this.date = date;
		this.start = start;
		this.stop = stop;
		this.deltaTime = deltaTime;
		this.lifeCycle = lifeCycle;
		this.category = category;
		this.etc = etc;
	}

	/**
	 * @param log - array of textfields that store the information the log needs in order
	 * Setter constructor that sets the information from the textfield array of user inputs.
	 */
	public Log(TextField[] log) {
		this.name = log[0].getText();
		this.role = log[1].getText();
		this.date = log[2].getText();
		this.start = log[3].getText();
		this.stop = log[4].getText();
		this.deltaTime = log[5].getText();
		this.lifeCycle = log[6].getText();
		this.category = log[7].getText();
		this.etc = log[8].getText();
	}
}
