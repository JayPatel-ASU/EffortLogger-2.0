package privacyPackage;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @author Raeed Azom
 * GridPane that represents the manager's view of data as a table. Includes functionality like hiding and refreshing data.
 */
public class ManagerView extends GridPane {
	private ArrayList<Log> logList;
	private boolean displayNames;

	/**
	 * Constructor that intiializes the title and top row of the table, as well as spacing
	 */
	public ManagerView() {
		super();
		Text textTitle = new Text("Manager View");
        textTitle.setStyle("-fx-font-size: 18px;");
		this.add(textTitle, 0, 0, 9, 1);
		this.add(new Text("Name"), 0, 1);
		this.add(new Text("Role"), 1, 1);
		this.add(new Text("Date"), 2, 1);
		this.add(new Text("Start"), 3, 1);
		this.add(new Text("Stop"), 4, 1);
		this.add(new Text("Delta Time"), 5, 1);
		this.add(new Text("Life Cycle Step"), 6, 1);
		this.add(new Text("Effort Category"), 7, 1);
		this.add(new Text("Deliverable / Interruption / etc."), 8, 1);
		this.setHgap(15);
		this.setVgap(4);
		this.logList = new ArrayList<>();
		this.displayNames = true;
	}

	/**
	 * @param log - text fields that represent the log
	 * @param threshold - threshold to start displaying items
	 * @param hideName - manager setting to show or hide names
	 * Basic overload that redirects to the main add function
	 */
	public void addLog(TextField[] log, int threshold, boolean hideName) {
		addLog(new Log(log), threshold, hideName);
	}

	/**
	 * @param log - text fields that represent the log
	 * @param threshold - threshold to start displaying items
	 * @param hideName - manager setting to show or hide names
	 * Adds the log to the list and either updates or clears the table depending on visibility threshold
	 */
	public void addLog(Log log, int threshold, boolean hideName) {
		if(hideName) {
			log.name = "N/A";
		}
		logList.add(log);
		if(logList.size() >= threshold) {
			showAll();
		} else {
			clearAll();
		}
	}

	/**
	 * Clears both arraylist and data, resetting the data
	 */
	public void resetData() {
		logList.clear();
		clearAll();
	}

	/**
	 * Toggles the name visibility and refreshes the table
	 * Could be made more efficient by only changing names, but I haven't been able to do that ye
	 */
	public void toggleNames() {
		if(displayNames) {
			displayNames = false;
			clearAll();
			showAll();
		} else {
			displayNames = true;
			clearAll();
			showAll();
		}
	}

	/**
	 * Displays data in the table from the arraylist,
	 * clearing it beforehand
	 */
	private void showAll() {
		clearAll();
		for(int i = 0; i < logList.size(); i++) {
			Log current = logList.get(i);
			if(displayNames) {
				this.add(new Text(current.name), 0, i+2);
			} else {
				this.add(new Text(""), 0, i+2);
			}
			this.add(new Text(current.role), 1, i+2);
			this.add(new Text(current.date), 2, i+2);
			this.add(new Text(current.start), 3, i+2);
			this.add(new Text(current.stop), 4, i+2);
			this.add(new Text(current.deltaTime), 5, i+2);
			this.add(new Text(current.lifeCycle), 6, i+2);
			this.add(new Text(current.category), 7, i+2);
			this.add(new Text(current.etc), 8, i+2);
		}
	}

	/**
	 * Clears the table, keeping just the title and top row.
	 */
	private void clearAll() {
		while(this.getChildren().size() > 10) {
			this.getChildren().remove(10);
		}
	}
}
