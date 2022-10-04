/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Constructs schedule for GUI
 * @author Kayna Henningham
 *
 */
public class WolfScheduler {
	/** declares new array list for catalog */
	private ArrayList<Course> catalog = new ArrayList<Course>();

	/** declares and initializes new array list for schedule */
	private ArrayList<Activity> schedule;
	
	/** declares title for schedule */
	private String title;

	/**
	 * Constructor method for schedule 
	 * @param filename filename of course record file
	 */
	public WolfScheduler(String filename) {
		schedule = new ArrayList<Activity>(); //Initializes schedule array list
		title = "My Schedule"; //sets default title
		
		try {
		//adds courses from courses array list to catalog array list
		catalog.addAll(CourseRecordIO.readCourseRecords(filename));
		}
		
		//if CourseRecordIO.readCourseRecords() throws an exception, throw IAE
		catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Method creates entire course catalog (2D string array) from catalog arraylist
	 * @return courseCatalog of courses
	 */
	public String[][] getCourseCatalog() {
        //declare a new 2D array for catalog
		String[][] courseCatalog = new String[catalog.size()][4];
		
		//loops through catalog to assign name, section, and title to 2D array	
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			
			courseCatalog[i][0] = c.getName();

			courseCatalog[i][1] = c.getSection();
		
			courseCatalog[i][2] = c.getTitle();
			
			courseCatalog[i][3] = c.getMeetingString();
		}
		
		// returns 2D array for catalog
		return courseCatalog;
	}

	/**
	 * Method returns a simple table of schedules courses
	 * @return scheduleCatalog 2D array of scheduled courses
	 */
	public String[][] getScheduledActivities() {
        //Declare a new 2D array for schedule
		String[][] scheduleCatalog = new String[schedule.size()][3];
		
		//loops through schedule and assigns name, section, and title to each course		
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
				scheduleCatalog[i] = a.getShortDisplayArray();
		}

		//returns 2D array of schedule
		return scheduleCatalog;
	}

	/**
	 * Method generates a full 2D string array schedule from schedule array list
	 * @return fullSchedule 2D array
	 */
	public String[][] getFullScheduledActivities() {
        
		//Declare a new 2D String array with 5 columns 
		String[][] fullSchedule = new String[schedule.size()][5];
		
		//loops through the schedule array list to assign all values to 2D array
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			fullSchedule[i] = a.getLongDisplayArray();
		}
		
		//returns 2D array of fullSchedule
		return fullSchedule;
	}

	/**
	 * Method returns schedule title
	 * @return title title of course
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Method gets each course from the catalog
	 * @param name name of course
	 * @param section section of course
	 * @return course at appropriate index in the catalog if true
	 */
	public Course getCourseFromCatalog(String name, String section) {
		//Iterate through catalog to find course, returns as soon as course is found
		Course c = null;
		boolean isCourse = false;
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				isCourse = true;
				c = catalog.get(i);
			}
		}
		
		if (!isCourse) {
			return null;
		}
		//return null if name and section don't match
		return c;
	}

	/**
	 * Method adds each course to the schedule and returns true if course exists
	 * @param name name of course
	 * @param section section of course
	 * @return true if the course exists and course is added, returns false otherwise
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
//	
//		if (c == null) {
//			return false;
//		}  
		
		for (int i = 0; i < schedule.size(); i++) {
		    //constructs new course object for comparison
			Activity c0 = schedule.get(i);

			//calls method to determine if course is a duplicate
			if (c.isDuplicate(c0)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
	    }
		
		return schedule.add(c);  
	}

	/**
	 * Method removes courses from schedule if the course is on the schedule
	 * @param name name of the course
	 * @param section of the course
	 * @return true if the course is on the schedule and can be removed
	 * @deprecated Use {@link #removeActivityFromSchedule(int)} instead
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		return removeActivityFromSchedule(-1);
	}

	/**
	 * Method removes courses from schedule if the course is on the schedule
	 * @param idx index of activity to be removed
	 * @return true if the course is on the schedule and can be removed
	 */
	public boolean removeActivityFromSchedule(int idx) {
		//removes activity if it can be removed
		try { 
			schedule.remove(idx);
			return true;
		}
		
		//catches exception
		catch (IndexOutOfBoundsException e) {
			return false;
			
		}
		
	}

	/** Method clears entire schedule and sets title back to default
	 * 
	 */
	public void resetSchedule() {
		schedule.clear(); //clears schedule array list
	}

	/**
	 * Method sets the title of the course
	 * @param title title of course
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) { //checks if title is null 
			throw new IllegalArgumentException("Title cannot be null"); //throws IAE if title is null
		}
		
		this.title = title;
    }

	/**
	 * Method writes file of schedule
	 * @param filename filename of course records 
	 * @throws IllegalArgumentException if file can't be saved
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule); //tries to write schedule to filename
		}
		
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved"); //catches IOException if file can't be saved
			
		}
	}

	/**
	 * Adds event to schedule 
	 * @param title title of event
	 * @param meetingDays meetingDays of event
	 * @param startTime startTime of event
	 * @param endTime endTime of event
	 * @param weeklyRepeat how many times event repeats per week
	 * @param eventDetails details of event
	 * @return true if event can be added, adds event, returns false otherwise
	 */
	public boolean addEventToSchedule(String title, String meetingDays, int startTime, int endTime,
			int weeklyRepeat, String eventDetails) {
		//constructs a new event object 
		Event event = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		
		for (int i = 0; i < schedule.size(); i++) {
			Activity event1 = schedule.get(i);
		    //calls abstract method to check if event is a duplicate
		    if (event.isDuplicate(event1)) {
			    throw new IllegalArgumentException("You have already created an event called " + title);
		    }
		}
	    //adds event to schedule
		return schedule.add(event);
		
	}

}
