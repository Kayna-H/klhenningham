/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class constructs events 
 * @author Kayna Henningham
 *
 */
public class Event extends Activity {
	
	/** Maximum amount of weekly repeats */
	private static final int MAX_REPEAT = 4;
	/** Minimum amount of weekly repeats */
	private static final int MIN_REPEAT = 1;
	/** Weekly report */
	private int weeklyRepeat;
	/** Event details */
	private String eventDetails;

	
	/**
	 * Constructor for event
	 * @param title title of course
	 * @param meetingDays meeting days of course
	 * @param startTime start time of course
	 * @param endTime end time of course
	 * @param weeklyRepeat of course
	 * @param eventDetails event details 
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Method gets weekly repeat
	 * @return weeklyRepeat amount of times event repeats in one week
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets weekly report
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		//Checks if weekly repeat is valid
		if (weeklyRepeat < MIN_REPEAT || weeklyRepeat > MAX_REPEAT) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		
		
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Gets event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets event details if they are valid 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		//Checks if event details are null
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		
		this.eventDetails = eventDetails;
	}

	/**
	 * Method returns a meeting string
	 * @return newString of meeting string
	 */
	@Override
	public String getMeetingString() {
		//Declare new string
		String newString = "";
		//Add items to string
		newString += super.getMeetingString();
		newString += " (every " + getWeeklyRepeat() + " weeks)";
		//return string
		return newString;
	}

	/**
	 * To string method that produces a string for the course and event
	 * @return newString 
	 */
	@Override
	public String toString() {
		//Declare new string
		String newString = "";
		
		//Add everything to string
		newString += getTitle() + ",";
		newString += getMeetingDays() + ",";
		newString += getStartTime() + ",";
		newString += getEndTime() + ",";
		newString += getWeeklyRepeat() + ",";
		newString += getEventDetails();
		
		//return string
		return newString;
	}

	/**
	 * Method returns a short string array for display
	 * @return display string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		
		//create a new string array with appropriate elements
		String[] shortDisplay = {"", "", getTitle(), getMeetingString()};
		
		//return string array 
		return shortDisplay;
	}

	/**
	 * Method returns a long string array for display
	 * @return display string array
	 */
	@Override
	public String[] getLongDisplayArray() {
        //create new string array
		String[] longDisplay = {"", "", getTitle(), "", "", getMeetingString(), getEventDetails()};
		
		//return string array 
		return longDisplay;
	}

	/**
	 * Method determines if event is a duplicate
	 * @return true if object is a duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		//checks if activity is an event 
		if (activity instanceof Event) {
			
			// constructs an event 
			Event e = (Event) activity;
			if (e.getTitle().equals(getTitle())) {
				return true;
			}		
		
		}
		return false;
		
	}
	
	/**
	 * Method sets meeting days and time
	 * @param meetingDays meeting days of event
	 * @param startTime start Time of event
	 * @param endTime end time of event
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) { 
			throw new IllegalArgumentException("Invalid meeting days.");
		}

		else {
		//declare variables for counters
		int monCount = 0;
		int tuesCount = 0;
		int wednesCount = 0;
		int thursCount = 0;
		int friCount = 0;
		int satCount = 0;
		int sunCount = 0;
					
		//loops through each day of the week, counts days, throws new IAE if counter is more than 1
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) == 'M') {
			    monCount++;
				if (monCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
						
			else if (meetingDays.charAt(i) == 'T') {
				tuesCount++;
				if (tuesCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
						
			else if (meetingDays.charAt(i) == 'W') {
				wednesCount++;
				if (wednesCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
						
			else if (meetingDays.charAt(i) == 'H') {
				thursCount++;
				if (thursCount > 1) {
				    throw new IllegalArgumentException("Invalid meeting days.");
				}
		    }
						
			else if (meetingDays.charAt(i) == 'F') {
				friCount++;
				if (friCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
				
			
			else if (meetingDays.charAt(i) == 'S') {
				satCount++;
				if (satCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			
			else if (meetingDays.charAt(i) == 'U') {
				sunCount++;
				if (sunCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			
			else {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
				
			}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
				
		}
	}
}
