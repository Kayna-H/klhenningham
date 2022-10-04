package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class constructs and sets values for activities
 * @author Kayna Henningham
 *
 */
public abstract class Activity {

	/** Upper hour. */
	private static final int UPPER_HOUR = 24;
	/** Upper minute. */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
    
	
	/**
	 * Constructor for activity
	 * @param title title of activity
	 * @param meetingDays meetingDays of activity
	 * @param startTime startTime of activity
	 * @param endTime endTime of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        //sets title, meeting days, start time of activity
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		//Conditional 1:
		if (title == null || "".equals(title)) { 
			throw new IllegalArgumentException("Invalid title.");
		}
	
		//Conditional 2:
		if (title == null || title.length() == 0) { 
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
	}

	/**
	 * Method gets meetingDays
	 * @return the meetingDays of course
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Method gets start time 
	 * @return the startTime of course
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Method gets end time of course
	 * @return the endTime of course
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Method sets Meeting Days, start time, and end time of course and makes sure all parameters are valid
	 * @param meetingDays days the course meets
	 * @param startTime start time of course
	 * @param endTime end time of course
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
	//Checks if meeting days are null
	if (meetingDays == null || "".equals(meetingDays)) { 
		throw new IllegalArgumentException("Invalid meeting days.");
	}
	
	//sets parameters for arranged meeting days
	if ("A".equals(meetingDays)) {
		this.meetingDays = meetingDays;
		this.startTime = 0;
		this.endTime = 0;
		return;
	}
	
	else {

		// break start time and end time into hours and mins
		int startHour;
		startHour = startTime / 100;
		
		int startMin;
		startMin = startTime % 100;
		
		int endHour;
		endHour = endTime / 100;
		
		int endMin;
		endMin = endTime % 100;
		
		//checks if start and end time is valid
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		
		if (startTime > endTime) {
			throw new IllegalArgumentException("End time cannot be before start time.");
		}
		
		//sets everything to its parameter
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	    }
	}

	/**
	 * Method generates a string for meeting time
	 * @return "Arranged" if meeting days is A, otherwise returns full meeting string
	 */
	public String getMeetingString() {
		//Checks if meetingDays are arranged
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
		
		//creates string to output
		String meetingString = "";
		meetingString += meetingDays + " ";
		meetingString += getTimeString(startTime);
		meetingString += "-";
		meetingString += getTimeString(endTime);
		return meetingString;
	}

	/**
	 * Method generates a string of time
	 * @param time time of class
	 * @return time string
	 */
	private String getTimeString(int time) {
		//splits time into hours and minutes
		int hours = time / 100;
		int mins = time % 100;
		String morningOrEvening = ""; 
		
		//Checks if time is AM or PM
		if (hours < 12) {
			morningOrEvening = "AM";
		}
		
		if (hours > 12) {
			morningOrEvening = "PM";
			hours -= 12;
		}
		
		//if hour is 0, set to 12
		if (hours == 0) {
			hours = 12;
			morningOrEvening = "AM";
		}
		
		//if hour is 12, set to PM
		if (hours == 12) {
			hours = 12;
			morningOrEvening = "PM";
		}
		
		//Converts hours and minutes to strings
		String hour = Integer.toString(hours);
		String min = Integer.toString(mins);
		
		//Adds leading 0 to minutes less than 10 
		if (mins < 10) {
			min = "0" + min;
		}
		
		//returns time string
		return hour + ":" + min + morningOrEvening; 	
	}
	
	/**
	 * Method creates short display array of activity
	 * @return string array of activities
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Method creates long display array of activity
	 * @return string array of activity
	 */
	public abstract String[] getLongDisplayArray();
	
	
	/**
	 * Method determines if activity is a duplicate
	 * @param activity activity to determine duplicate
	 * @return true if the activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity); 

	/**
	 * Method generates hash code for comparison
	 * @return result of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Method determines if two objects are equal
	 * @return true if objects are equal, returns false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}