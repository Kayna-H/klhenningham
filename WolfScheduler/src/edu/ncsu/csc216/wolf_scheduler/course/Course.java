/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Constructs course objects 
 * @author Kayna Henningham
 *
 */
public class Course extends Activity {
	
	/** Minimum name length of Course. */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum name length of Course. */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum letter count. */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum letter count. */
	private static final int MAX_LETTER_COUNT = 4;
	/** Number of digit count */
	private static final int DIGIT_COUNT = 3;
	/** Length of section. */
	private static final int SECTION_LENGTH = 3;
	/** Maximum number of credits allowed. */
	private static final int MAX_CREDITS = 5;
	/** Minimum number of credits allowed. */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
            int startTime, int endTime) {
       //sets parameters to construct course object
		super(title, meetingDays, startTime, endTime);
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
    }
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		//Throws exception if name is null
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null.");
		}
	    
		//Throws exception for empty string and strings outside of 5-8
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH || "".equals(name)) {
			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
		}
		
		//Check for correct pattern 
		int letterCounter = 0;
	    int digitCounter = 0;
		boolean isSpace = false;
		for (int i = 0; i < name.length(); i++) {
		    if (!isSpace) {
			    if (Character.isLetter(name.charAt(i))) {
			    	letterCounter++;
			    }
			    
			    else if (name.charAt(i) == ' ') {
			        isSpace = true;
		        }
			    
			    else { 
			        throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		        }
		    }
			 
		    else if (isSpace) {
		    	if (Character.isDigit(name.charAt(i))) {
		    		digitCounter++;
		    	}
		    	
		    	else {
		    		throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		    	}
		    }
		}
		
		//Check that the number of letters is correct
		if (letterCounter < MIN_LETTER_COUNT && letterCounter > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}
		
		//Check that the number of digits is correct
		if (digitCounter != DIGIT_COUNT) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");					}
		
		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * @return section section of course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Method makes sure section is valid, then sets section to parameter
	 * @param section the section to set
	 */
	public void setSection(String section) {
		
		//Checks if section is null
		if (section == null) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		//Checks if section length is the correct length
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		//Makes sure section is the correct length
		for (int i = 0; i < section.length(); i++) {
			if (!(Character.isDigit(section.charAt(i)))) {
				throw new IllegalArgumentException("Section should be three digits.");
			}
		}
			
		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * @return credits for course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Method checks that credits is valid, then sets credits to parameter
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		//Checks if credits is valid
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		
		//sets credits to parameter
		this.credits = credits;
	}

	/**
	 * Method gets Instructor Id
	 * @return the instructorId of course
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Checks if InstructorID is valid
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		//Checks if instructor ID is valid
		if (instructorId == null || "".equals(instructorId)) { 
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		//sets instructorID to parameter
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    //special case if meeting days are arranged
		if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 

	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}


	/**
	 * Method returns a short string array for display
	 * @return display string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		
		//create a new string array with appropriate elements
		String[] shortDisplay = {getName(), getSection(), getTitle(), getMeetingString()};
		
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
		String[] longDisplay = {getName(), getSection(), getTitle(), Integer.toString(getCredits()), getInstructorId(), getMeetingString()};
		
		//return string array 
		return longDisplay;
	}

	/**
	 * Method determines if courses are duplicates
	 * @return true if the course is a duplicate, returns false otherwise
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		
		//Checks if activity is a course
		if (activity instanceof Course) {
			//constructs new course object
			Course c = (Course) activity;
			//Checks if courses are equal
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return true;
			}
		}
		
		else {
			return false;
		}
		
		return false;
	}
		
	/**
	 * Method sets meeting days and time string 
	 */
	@Override 
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) { 
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		
		//sets parameters for arranged meeting days
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime("A", 0, 0);
			return;
		}
		
		else {
	    //declare variables for counters
		int monCount = 0;
		int tuesCount = 0;
		int wednesCount = 0;
		int thursCount = 0;
		int friCount = 0;
			
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
			
			//if meeting days are none of the above
			else {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
				
		}
		
		//set equal to parameter
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			
	    }
	}
}
