package solution_Expeditors_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import solution_Expeditors.Main;
import solution_Expeditors.Person;


/** 
 * 
 * @author Dustin Yoo
 * Goal of this unit test is not focused on code coverage, so achieving maximal code coverage was not what I was aiming for here.
 * The goal was to attempt to prove that my code is correct, to help design it better, or help someone else understand what the code is meant to do.
 * One implementation I would have added is to use something I learned during this assignment: Mockito, to verify the calls of our methods.
 * By verifying the calls of methods, I could track down the call of each method to understand the flow of the application to potentially design it better.
 *
 */
class MainTest {
	public static final String testFileName = "src/solution_Expeditors_test/inputTest.txt";

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;
	
	
	// Here I wanted to take an approach to call the computeOut() to test that the outputs were correct, or even working at all.
	// Unfortunately I wasn't able to figure out how to write tests for system outputs in the time that I wanted to finish, but the section that is commented out is the direction I was going for
//	@Before
//	public void setUpStreams() {
//	    System.setOut(new PrintStream(outContent));
//	}
//	
//	@After
//	public void restoreStreams() {
//	    System.setOut(originalOut);
//	    System.setErr(originalErr);
//	}
//	
//	
//	@Test
//	public void computeOutputTest() {
//		System.out.print("hello");
//		List<Person> listTest = Main.readFile(testFileName);
//		Map<String, List<Person>> mapTest = Main.populateMap(listTest);
//		assertEquals(Main.computeOutput(mapTest), outContent.toString());
//	}
	
	
	// Testing method that populates our map, checking to see if data is placed into map.
	@Test
	public void populateMapTest() throws IOException {
		List<Person> listTest = Main.readFile(testFileName);
		assertTrue("Map is populated", !Main.populateMap(listTest).isEmpty());
		assertFalse("Map is populated", Main.populateMap(listTest).isEmpty());
		
		List<Person> emptyListTest = new ArrayList<>();
		assertEquals(0, Main.populateMap(emptyListTest).size());
		assertTrue("Map is empty with empty list", Main.populateMap(emptyListTest).isEmpty());
		
	}
	
	
	// Does not test for IOException. I feel that they are well tested enough standard library classes
	@Test
	public void readFileTest() throws IOException {
		List<Person> result = Main.readFile(testFileName);
		assertEquals(6, result.size());
		assertTrue("List is not empty", result.size() != 0);
	}
	
	// Testing for file not found exception
	@Test
	public void readFileTestFileNotFoundException() {
		String nonExistingFile = "abc.txt";
		Throwable exception = assertThrows(FileNotFoundException.class, () -> Main.readFile(nonExistingFile));
		assertEquals(nonExistingFile + " (The system cannot find the file specified)", exception.getMessage());
	}
	

	
}
