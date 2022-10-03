/**
 * 
 */
package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests ReportManager methods
 * 
 * @author Osama Albahrani
 *
 */
public class ReportManagerTest {
	/** Path to a sample csv of rooms */
	private static final String ROOMS_PATH = "input/sample-rooms.csv";
	/** Path to a sample csv of logs */
	private static final String LOGS_PATH = "input/sample-logs.csv";
	/** Path to an empty csv of rooms */
	private static final String ROOMS_EMPTY_PATH = "input/sample-rooms-empty.csv";
	/** Path to an empty csv of logs */
	private static final String LOGS_EMPTY_PATH = "input/sample-logs-empty.csv";

//	private static final String FORMAT = "MM/dd/yyyy HH:mm:ss";
//	/** The indentation of 3 spaces for rooms in the output */
//	private static final String FIRST_INDENTATION = "   ";
//	/** The indentation of 6 spaces for cleaning times in the output */
//	private static final String SECOND_INDENTATION = "      ";

	private ReportManager manager;
	private ReportManager managerEmpty;

	
	/**
	 * Sets up the values of the fields before tests.
	 */
	@Before
	public void setUp() {
		try {
			manager = new ReportManager(ROOMS_PATH, LOGS_PATH);
			managerEmpty = new ReportManager(ROOMS_EMPTY_PATH, LOGS_EMPTY_PATH);
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc316.cleaning.manager.ReportManager#getVacuumBagReport(java.lang.String)}.
	 */
	@Test
	public final void testGetVacuumBagReport() {
		String unixTime = "01/01/1910 00:00:00";
		
		// No rooms
		String s = managerEmpty.getVacuumBagReport(unixTime);
		assertEquals("Vacuum Bag Report (last replaced 01/01/1910 00:00:00) [\n"
				+ "   Bag is due for replacement in 5280 SQ FT\n"
				+ "]\n", s);
		
		// All covered
		s = manager.getVacuumBagReport(unixTime);
		assertEquals("Vacuum Bag Report (last replaced 01/01/1910 00:00:00) [\n"
				+ "   Bag is overdue for replacement!\n"
				+ "]\n", s);
		
		// All covered
		String futureTime = "01/01/2023 00:00:00";
		s = manager.getVacuumBagReport(futureTime);
		assertEquals("Vacuum Bag Report (last replaced 01/01/2023 00:00:00) [\n"
				+ "   Bag is due for replacement in 5280 SQ FT\n"
				+ "]\n", s);
		
		// Barely covered
		String oneBarelyTime = "06/01/2021 13:39:01";
		assertEquals("Vacuum Bag Report (last replaced 06/01/2021 13:39:01) [\n"
				+ "   Bag is due for replacement in 5280 SQ FT\n"
				+ "]\n", manager.getVacuumBagReport(oneBarelyTime));
		
		// One covered
		String oneTime = "06/01/2021 13:39:00";
		assertEquals("Vacuum Bag Report (last replaced 06/01/2021 13:39:00) [\n"
				+ "   Bag is due for replacement in 5149 SQ FT\n"
				+ "]\n", manager.getVacuumBagReport(oneTime));
		
		// Invalid
		assertEquals("Date & time must be in the format: MM/DD/YYYY HH:MM:SS",
				manager.getVacuumBagReport("1:00PM"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.cleaning.manager.ReportManager#getFrequencyReport(int)}.
	 */
	@Test
	public final void testGetFrequencyReport() {
		// UC2: View the Most Frequently Cleaned Rooms
		
		// Test empty manager
		assertEquals("Number of rooms must be greater than 0.", managerEmpty.getFrequencyReport(-1));
		assertEquals("No rooms have been cleaned.", managerEmpty.getFrequencyReport(1));
		assertEquals("No rooms have been cleaned.", managerEmpty.getFrequencyReport(2));

		
		assertEquals("Number of rooms must be greater than 0.", manager.getFrequencyReport(-1));
		assertEquals("Number of rooms must be greater than 0.", manager.getFrequencyReport(0));
		assertEquals("Frequency of Cleanings [\n"
				+ "   Living Room has been cleaned 6 times\n"
				+ "]\n", manager.getFrequencyReport(1));
		assertEquals("Frequency of Cleanings [\n"
				+ "   Living Room has been cleaned 6 times\n"
				+ "   Dining Room has been cleaned 3 times\n"
				+ "   Guest Bathroom has been cleaned 2 times\n"
				+ "]\n", manager.getFrequencyReport(3));
		assertEquals("Frequency of Cleanings [\n"
				+ "   Living Room has been cleaned 6 times\n"
				+ "   Dining Room has been cleaned 3 times\n"
				+ "   Guest Bathroom has been cleaned 2 times\n"
				+ "   Guest Bedroom has been cleaned 2 times\n"
				+ "   Foyer has been cleaned 1 times\n"
				+ "   Office has been cleaned 1 times\n"
				+ "   Kitchen has been cleaned 0 times\n"
				+ "]\n", manager.getFrequencyReport(7));
		assertEquals("Frequency of Cleanings [\n"
				+ "   Living Room has been cleaned 6 times\n"
				+ "   Dining Room has been cleaned 3 times\n"
				+ "   Guest Bathroom has been cleaned 2 times\n"
				+ "   Guest Bedroom has been cleaned 2 times\n"
				+ "   Foyer has been cleaned 1 times\n"
				+ "   Office has been cleaned 1 times\n"
				+ "   Kitchen has been cleaned 0 times\n"
				+ "]\n", manager.getFrequencyReport(1000));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.cleaning.manager.ReportManager#getRoomReport()}.
	 */
	@Test
	public final void testGetRoomReport() {
		// Test empty manager
		assertEquals("No rooms have been cleaned.", managerEmpty.getRoomReport());

		
		
		assertEquals("Room Report [\n"
				+ "   Dining Room was cleaned on [\n"
				+ "      05/31/2021 09:27:45\n"
				+ "      05/23/2021 18:22:11\n"
				+ "      05/21/2021 09:16:33\n"
				+ "   ]\n"
				+ "   Foyer was cleaned on [\n"
				+ "      05/01/2021 10:03:11\n"
				+ "   ]\n"
				+ "   Guest Bathroom was cleaned on [\n"
				+ "      05/17/2021 04:37:31\n"
				+ "      05/08/2021 07:01:51\n"
				+ "   ]\n"
				+ "   Guest Bedroom was cleaned on [\n"
				+ "      05/23/2021 11:51:19\n"
				+ "      05/13/2021 22:20:34\n"
				+ "   ]\n"
				+ "   Kitchen was cleaned on [\n"
				+ "      (never cleaned)\n"
				+ "   ]\n"
				+ "   Living Room was cleaned on [\n"
				+ "      05/30/2021 10:14:41\n"
				+ "      05/28/2021 17:22:52\n"
				+ "      05/12/2021 18:59:12\n"
				+ "      05/11/2021 19:00:12\n"
				+ "      05/09/2021 18:44:23\n"
				+ "      05/03/2021 17:22:52\n"
				+ "   ]\n"
				+ "   Office was cleaned on [\n"
				+ "      06/01/2021 13:39:01\n"
				+ "   ]\n"
				+ "]\n", manager.getRoomReport());
		
		// Get it again
		assertEquals("Room Report [\n"
				+ "   Dining Room was cleaned on [\n"
				+ "      05/31/2021 09:27:45\n"
				+ "      05/23/2021 18:22:11\n"
				+ "      05/21/2021 09:16:33\n"
				+ "   ]\n"
				+ "   Foyer was cleaned on [\n"
				+ "      05/01/2021 10:03:11\n"
				+ "   ]\n"
				+ "   Guest Bathroom was cleaned on [\n"
				+ "      05/17/2021 04:37:31\n"
				+ "      05/08/2021 07:01:51\n"
				+ "   ]\n"
				+ "   Guest Bedroom was cleaned on [\n"
				+ "      05/23/2021 11:51:19\n"
				+ "      05/13/2021 22:20:34\n"
				+ "   ]\n"
				+ "   Kitchen was cleaned on [\n"
				+ "      (never cleaned)\n"
				+ "   ]\n"
				+ "   Living Room was cleaned on [\n"
				+ "      05/30/2021 10:14:41\n"
				+ "      05/28/2021 17:22:52\n"
				+ "      05/12/2021 18:59:12\n"
				+ "      05/11/2021 19:00:12\n"
				+ "      05/09/2021 18:44:23\n"
				+ "      05/03/2021 17:22:52\n"
				+ "   ]\n"
				+ "   Office was cleaned on [\n"
				+ "      06/01/2021 13:39:01\n"
				+ "   ]\n"
				+ "]\n", manager.getRoomReport());

	}

}
