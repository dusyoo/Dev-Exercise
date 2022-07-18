package solution_Expeditors;

/**
 * 
 * @author Dustin Yoo
 * Our Person object to create Persons
 * Class contains setters and getters in case a "Person" changes their name, age, address, etc. for a more good practice approach.
 * And setters and getters allows us to extend off this application.
 * 
 */

public class Person {
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String cityAddress;
	private String stateAddress;
	private String age;
	
	public Person(String[] personData) {
		this.firstName = personData[0];
		this.lastName = personData[1];
		this.streetAddress = personData[2];
		this.cityAddress = personData[3];
		this.stateAddress = personData[4];
		this.age = personData[5];
	}
	
	// Checks to see if our string value of age is older than 18 (non-inclusive)
	// Returns true or false
	public boolean isAdult() {
		return Integer.valueOf(age) > 18;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}
	
	public String getCityAddress() {
		return cityAddress;
	}
	
	public void setStateAddress(String stateAddress) {
		this.stateAddress = stateAddress;
	}
	
	public String getStateAddress() {
		return stateAddress;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}
}
