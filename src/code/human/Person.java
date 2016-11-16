package code.human;

public class Person {
	private String ID;
	private String name;
	private String lastName;
	private String gender;
	private int age;

	public Person() {
		ID = "0000";
		name = "";
		lastName = "";
		gender = "";
		age = 0;
	}

	public Person(String ID, String name, String lastName, String gender, int age) {
		this.ID = ID;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}

	public String toString() {
		return ID + ", " + name + " " + lastName + ", " + gender + ", " + age;
	}
}