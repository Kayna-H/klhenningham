/**
 * 
 */
package edu.ncsu.csc316.cleaning.factory;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests DSAFactory constructor
 * 
 * @author Osama Albahrani
 *
 */
public class DSAFactoryTest {

	/**
	 * Test the implicit constructor
	 */
	@Test
	public final void testDSAFactory() {
		DSAFactory factory = new DSAFactory();
		assertTrue(factory instanceof DSAFactory);
	}

}
