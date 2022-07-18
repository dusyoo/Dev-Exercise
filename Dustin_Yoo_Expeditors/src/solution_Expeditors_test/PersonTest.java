package solution_Expeditors_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import solution_Expeditors.Person;

/**
 * 
 * @author Dustin Yoo
 * Testing Person object
 *
 */
class PersonTest {
	
	// Testing constructor by calling the constructor and checking if values are expected
	// Also quickly testing getters
	@Test
	public void PersonConstructorTest() {
		Person personTest1 = new Person(new String[] {"bob","smith","123 st","seattle","wa","20"});
		Person personTest2 = new Person(new String[] {"frank","smith","321 st","seattle","wa","20"});
		Person personTest3 = new Person(new String[] {"bob","jjj","123 st","seattle","wa","20"});
		Person personTest4 = new Person(new String[] {"jane","smith","123 st","seattle","wa","2"});
		
		assertNotEquals(personTest1.getFirstName(), personTest2.getFirstName());
		assertEquals(personTest1.getFirstName(), personTest3.getFirstName());
		
		assertNotEquals(personTest1.getLastName(),personTest3.getLastName());
		
		assertEquals(personTest1.getStreetAddress(), personTest3.getStreetAddress());

		assertEquals(personTest3.getCityAddress(),personTest4.getCityAddress());
		
		assertEquals(personTest1.getStateAddress(),personTest2.getStateAddress());
	}
	
	// Testing isAdult() output
	@Test
	public void PersonIsAdultTest() {
		Person personTest1 = new Person(new String[] {"bob","smith","123 st","seattle","wa","20"});
		Person personTest2 = new Person(new String[] {"jane","smith","123 st","seattle","wa","2"});
		assertTrue(personTest1.isAdult());
		assertFalse(personTest2.isAdult());
	}
}
