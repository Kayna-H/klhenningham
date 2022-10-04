/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 *
 * @author Kayna Henningham
 */
public class CourseRecordIO {
	
	/**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param filename file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String filename) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filename));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            String fileLine = fileReader.nextLine();
	        	Course course = readCourse(fileLine); 
	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
						break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Method reads each line of the file and creates an object from the line
	 * @param nextLine line of the file
	 * @return newCourse constructor by file line
	 * @throws IAE if a NoSuchElementException is caught
	 */
	private static Course readCourse(String nextLine) {
		//Constructs new course object
		Course newCourse = null;
		Scanner fileReader = new Scanner(nextLine);  //Create a file scanner to read the line
		fileReader.useDelimiter(","); //change delimiter to a comma 
		
		try {
			//Read through line and set variables
			String name = fileReader.next();
			String title = fileReader.next(); 
			String section = fileReader.next(); 
			int credits = fileReader.nextInt(); 
			String instructorId = fileReader.next(); 
			String meetingDays = fileReader.next(); 
			int startTime;
			int endTime;
			//Special case meeting days are arranged
			if ("A".equals(meetingDays)) {
				startTime = 0;
				endTime = 0;
				//if the line has more information after A, line is invalid
				if (fileReader.hasNext()) {
					fileReader.close();
					throw new IllegalArgumentException();
				}
			}
				
			else {
			    startTime = fileReader.nextInt(); 
			    endTime = fileReader.nextInt(); 
			}
			
			//Constructs new object
			newCourse = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime); //constructs a new course object
			
		} catch (NoSuchElementException e) { 
			fileReader.close(); //closes scanner
			throw new IllegalArgumentException();
		}
		fileReader.close(); //closes scanner
		return newCourse;
	}
    
}

