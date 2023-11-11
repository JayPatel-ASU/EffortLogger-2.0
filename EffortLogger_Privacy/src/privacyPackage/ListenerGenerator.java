package privacyPackage;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * @author Raeed Azom
 * Static class to generate action and event listeners for the application
 * Useful for ones that might need to be reused or are lengthy
 */
public class ListenerGenerator {


	/**
	 * @param managerView - manager view of logs
	 * @return listener that toggles names on click
	 */
	public static ChangeListener<Boolean> viewNamesListener(ManagerView managerView) {
		return new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				managerView.toggleNames();
			}
		};
	}

	/**
	 * @param text - text box to make numerical inputs only
	 * @return listener that forces a textbox to accept numerical inputs only
	 */
	public static ChangeListener<String> numberTextListener(TextField text) {
		return new ChangeListener<> () {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// Regex checks the input data and deletes all non characters
				if (!newValue.matches("\\d*")) {
					text.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		};
	}

	/**
	 * @param managerView - manager view of logs
	 * @return listener that calls the resetdata function when clicked
	 */
	public static EventHandler<ActionEvent> clearDataListener(ManagerView managerView) {
		return new EventHandler<>() {
			@Override
			public void handle(ActionEvent e) {
				managerView.resetData();
			}
		};
	}

	/**
	 * @param managerView - manager view of logs
	 * @param boxes - array of all checkbox config options to the add function
	 * @param texts - array of all textbox config options for the add function
	 * @param inputData - log specified by the user
	 * @return listener that takes all relevant info from the application and sends it to the manager view
	 */
	public static EventHandler<ActionEvent> addDataListener(ManagerView managerView, CheckBox[] boxes, TextField[] texts, TextField[] inputData) {
		return new EventHandler<>() {
			@Override
			public void handle(ActionEvent e) {
				//Initializing important variables using the inputs
				Random r = new Random();
				int thresholdSize = Integer.parseInt(texts[0].getText());
				boolean hideNames = boxes[0].isSelected();
				int quantity = Integer.parseInt(texts[1].getText());
				// If we are batch sending and it is randomized
				if(boxes[1].isSelected() && boxes[2].isSelected()) {
					for(int i = 0; i < quantity; i++) {
						// Create random values for the log
						String date = "2005-" + r.nextInt(10, 13) + "-" + r.nextInt(10, 31);
						int hours = r.nextInt(10, 20);
						int minutes = r.nextInt(10, 40);
						int seconds = r.nextInt(10, 40);
						int addMin = r.nextInt(10, 21);
						int addSec = r.nextInt(10, 21);
						int category = r.nextInt(1, 10);
						// Combine them all to make the log
						Log log = new Log("User" + i,
								"Level " + r.nextInt(10) + " Employee",
								date,
								hours + ":" + minutes + ":" + seconds,
								hours + ":" + (minutes + addMin) + ":" + (seconds + addSec),
								addMin + ":" + addSec,
								"Project Step " + r.nextInt(1, 4),
								"Category " + category,
								"Deliverable " + category);
						// Send the log
						managerView.addLog(log, thresholdSize, hideNames);
					}
				// If we are batch sending but not randomizing
				} else if (boxes[1].isSelected()) {
					// Just send it quantitiy times
					for(int i = 0; i < quantity; i++) {
						managerView.addLog(inputData, thresholdSize, hideNames);
					}
				} else {
					// Otherwise just send it once
					managerView.addLog(inputData, thresholdSize, hideNames);
				}
			}
		};
	}

}