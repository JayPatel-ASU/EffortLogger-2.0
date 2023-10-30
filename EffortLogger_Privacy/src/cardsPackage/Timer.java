package cardsPackage;


public class Timer {

	// *********** ATTRIBUTES ***********
	private double duration;
	private double remainingTime;
	private boolean isStarted;
	private boolean isFinished;

	// Constructor
	public Timer (double duration) {
		this.duration = duration;
		remainingTime = duration;
		isStarted = false;
		isFinished = false;
	}

	public void Update() {
		// If timer is started and there is time remaining
		if (isStarted && !isFinished) {
			if (remainingTime > 0) {
				remainingTime--;
			} else {
				isFinished = true; // Time's up
			}
		}
	}

	// ********** METHODS **************

	// Starts/unpauses the timer
	public void start() {
		isStarted = true;
	}

	// Stops/pauses the timer
	public void stop() {
		isStarted = false;
	}

	// Reset timer to state before starting
	public void reset() {
		isStarted = false;
		isFinished = false;
		remainingTime = duration;
	}

	// ****** GETTERS AND SETTERS ******************

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public boolean getFinishedState() {
		return isFinished;
	}
}
