package solution_Expeditors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Dustin Yoo
 * This class handles reading and writing the output, along with handling the data given to each person's corresponding addresses/household
 * If I could have extended this application, I would've made a separate class to handle our data, specifically the populateMap() and computeOutput().
 * But given that the data size is rather small, I felt that combining them all within their own method was simple enough to get the job done, for now.
 * 
 */
public class Main {
	public static List<Person> listOfPersons;
	public static Map<String, List<Person>> addressAndOccupants;
	
	// Main to call methods for our application
	public static void main(String[] args) throws IOException {
		// Hard coding the filename
		// Could extend off this and create a method to take in a file name to locate its path
		String fileName = "src/solution_Expeditors/input.txt";
		listOfPersons = readFile(fileName);
		
		addressAndOccupants = populateMap(listOfPersons);
		
		computeOutput(addressAndOccupants);
	}
	
	// Method to compute the output
	// Expected output: Displays each household's address with the number of occupants (INCLUDING occupants under the age of 19), followed by
	// Each First Name, Last Name, Address and Age sorted by Last Name then First Name where occupant(s) is OLDER than 18
	public static void computeOutput(Map<String, List<Person>> map) {
		// iterate over map and grab key
		for (String key : map.keySet()) {
			// Produce output of which address (key) and size
			System.out.println("Household " + key + " with " + map.get(key).size() + " occupant(s)");
			
			// Sort the list by last name, then first name
			map.get(key).sort(Comparator.comparing(Person::getLastName)
					.thenComparing(Person::getFirstName));
			
			// For each person within an address (key), if they are over 18 (calls p.isAdult), display them under their respective addresses
			for (Person p : map.get(key)) {
				if (p.isAdult()) {
					System.out.println(p.getFirstName() + " " + 
										p.getLastName() + " " + 
										p.getStreetAddress() + " " + 
										p.getCityAddress() + " " + 
										p.getStateAddress() + " " + 
										p.getAge());
				}
			}
		}
	}
	
	// Method to populate our Map<String, List<Person>> addressAndOccupants
	// Since this application's goal was to group every individual to their homes, 
	// my approach was to take each person's address as a "key" to GROUP them together. So that is why I went with a HashMap approach.
	public static Map<String, List<Person>> populateMap(List<Person> list) {
		Map<String, List<Person>> map = new HashMap<>();
		// Iterate through our List and retrieve data to place into our map with their corresponding addresses
		for (int i = 0; i < list.size(); i++) {
			String address = list.get(i).getStreetAddress().toUpperCase() + " " + 
					list.get(i).getCityAddress().toUpperCase() + " " + 
					list.get(i).getStateAddress().toUpperCase();
			
			// Does the map already contain a key with an address?
			// If not, create a new key with the address and initialize a new ArrayList
			if (!map.containsKey(address)) {
				map.put(address, new ArrayList<>());
			}
			
			// Add the person into the corresponding address map key
			map.get(address).add(list.get(i));
		}
		return map;
	}
	
	// Helper method to populate our List<Person> listOfPerson to store a list of individual Persons
	private static void populatePersonList(String[] personData, List<Person> list) {
		// Cleaning up data
		for (int i = 0; i < personData.length; i++) {
			// Deletes all the periods, commas, double quotations and leading/trailing white spaces to clean up data
			personData[i] = personData[i].replaceAll("[,.\"]", "").trim();
		}
		
		Person person = new Person(personData);
		list.add(person);
	}
	
	// Read file method to locate and read the data within the file
	// Param: String fileName
	// Takes in file path as a string to use for our BufferedReader
	// I approached this method to read files and save them in a data structure at the same time, so I created our helper function to handle the saving into a list for ease of access. 
	public static List<Person> readFile(String fileName) throws IOException {
		List<Person> list = new ArrayList<>();
		String[] personData;
		
		// Using Java's try-with-resources to read strings to initialize our Person object per line in file
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();
			// Grabs first line and split at every comma double quote (,").
			// Note that this would not work if the data given were to be assigned with white spaces between every comma double quote.
			// Eg: "Dave","Smith" vs "Dave", "Smith" .split(",\"") vs .split(",\\s+\"").
			// When handling data, I like to make sure that our data maintains consist format to minimize incorrect data or errors in our code, and since the given input values all maintained no space between commas and double quotes,
			// I felt that just simply splitting at ," was good enough.
			// If required to handle inconsistent formatting, I would've approached this by adding further logic to correctly format the data given, line by line.
			personData = line.split(",\"");
			populatePersonList(personData, list);
			
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					// Reading every line that comes after the first and splitting the data
					personData = line.split(",\"");
					populatePersonList(personData, list);
				}
			}
		}
		return list;
	}
}
