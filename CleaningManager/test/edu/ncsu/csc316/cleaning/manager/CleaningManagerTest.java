/**
 * 
 */
package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
//import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests CleaningManager methods
 * @author Osama Albahrani
 *
 */
public class CleaningManagerTest {
	/** Path to a sample csv of rooms */
	private static final String ROOMS_PATH = "input/sample-rooms.csv";
	/** Path to a sample csv of logs */
	private static final String LOGS_PATH = "input/sample-logs.csv";
	
	/** The indentation of 3 spaces for rooms in the output */
	private static final String FIRST_INDENTATION = "   ";
//	/** The indentation of 6 spaces for cleaning times in the output */
//	private static final String SECOND_INDENTATION = "      ";

	private CleaningManager manager;
	
	/**
	 * Sets up the values of the fields before tests.
	 */
	@Before
	public void setUp() {
		try {
			manager = new CleaningManager(ROOMS_PATH, LOGS_PATH);
		} catch (FileNotFoundException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.cleaning.manager.CleaningManager#getEventsByRoom()}.
	 */
	@Test
	public final void testGetEventsByRoom() {
		Map<String, List<CleaningLogEntry>> map = manager.getEventsByRoom();
		
		// Check room IDs
//		String s = "";
//		for(Entry<String, List<CleaningLogEntry>> entry : map.entrySet()) {
//			s += entry.getKey();
//			s += "\n";
//		}
//		
//		assertEquals("", map.get("Dining Room").get(0));;
//		
//		assertEquals("Dining Room\n"
//				+ "Foyer\n"
//				+ "Guest Bathroom\n"
//				+ "Guest Bedroom\n"
//				+ "Kitchen\n"
//				+ "Living Room\n"
//				+ "Office\n", s);
		
		// Check logs
		String s = "";
		String[] roomsIDs = {"Dining Room",
				"Foyer",
				"Guest Bathroom",
				"Guest Bedroom",
				"Kitchen",
				"Living Room",
				"Office"};
		for(int i = 0; i < roomsIDs.length; i++) {
			String id = roomsIDs[i];
			s += id;
			s += " [\n";
			for(CleaningLogEntry cleaning : map.get(id)) {
				s += FIRST_INDENTATION;
				s += cleaning;
				s += "\n";
			}
			s += "]\n";
		}
		assertEquals("Dining Room [\n"
				+ "   CleaningLogEntry [timestamp=2021-05-31T09:27:45, roomID=Dining Room, percentCompleted=89]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-23T18:22:11, roomID=Dining Room, percentCompleted=89]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-21T09:16:33, roomID=Dining Room, percentCompleted=86]\n"
				+ "]\n"
				+ "Foyer [\n"
				+ "   CleaningLogEntry [timestamp=2021-05-01T10:03:11, roomID=Foyer, percentCompleted=93]\n"
				+ "]\n"
				+ "Guest Bathroom [\n"
				+ "   CleaningLogEntry [timestamp=2021-05-17T04:37:31, roomID=Guest Bathroom, percentCompleted=91]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-08T07:01:51, roomID=Guest Bathroom, percentCompleted=91]\n"
				+ "]\n"
				+ "Guest Bedroom [\n"
				+ "   CleaningLogEntry [timestamp=2021-05-23T11:51:19, roomID=Guest Bedroom, percentCompleted=77]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-13T22:20:34, roomID=Guest Bedroom, percentCompleted=74]\n"
				+ "]\n"
				+ "Kitchen [\n"
				+ "]\n"
				+ "Living Room [\n"
				+ "   CleaningLogEntry [timestamp=2021-05-30T10:14:41, roomID=Living Room, percentCompleted=68]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-28T17:22:52, roomID=Living Room, percentCompleted=70]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-12T18:59:12, roomID=Living Room, percentCompleted=94]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-11T19:00:12, roomID=Living Room, percentCompleted=89]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-09T18:44:23, roomID=Living Room, percentCompleted=89]\n"
				+ "   CleaningLogEntry [timestamp=2021-05-03T17:22:52, roomID=Living Room, percentCompleted=92]\n"
				+ "]\n"
				+ "Office [\n"
				+ "   CleaningLogEntry [timestamp=2021-06-01T13:39:01, roomID=Office, percentCompleted=78]\n"
				+ "]\n", s);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.cleaning.manager.CleaningManager#getCoverageSince(java.time.LocalDateTime)}.
	 */
	@Test
	public final void testGetCoverageSince() {
		// All covered
		LocalDateTime unixTime = LocalDateTime.parse( "01/01/1910 00:00:00", ReportManager.DATE_TIME_FORMAT);
		assertEquals(5727, manager.getCoverageSince(unixTime));
		
		// None covered
		LocalDateTime futureTime = LocalDateTime.parse( "01/01/2023 00:00:00", ReportManager.DATE_TIME_FORMAT);
		assertEquals(0, manager.getCoverageSince(futureTime));
		
		// Barely covered
		LocalDateTime oneBarelyTime = LocalDateTime.parse( "06/01/2021 13:39:01", ReportManager.DATE_TIME_FORMAT);
		assertEquals(0, manager.getCoverageSince(oneBarelyTime));
		
		// One covered
		LocalDateTime oneTime = LocalDateTime.parse( "06/01/2021 13:39:00", ReportManager.DATE_TIME_FORMAT);
		assertEquals(131, manager.getCoverageSince(oneTime));
	}

}