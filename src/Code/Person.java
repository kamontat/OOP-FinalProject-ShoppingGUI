package Code;

public class Person {
	private String ID;
	private String name;
	private String lastname;
	private String gender;
	private int age;

	public Person() {
		ID = "";
		name = "";
		lastname = "";
		gender = "";
		age = 0;
	}

	public Person(String ID, String name, String lastname, String gender, int age) {
		this.ID = ID;
		this.name = name;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
	}

	public Person(Person other) {
		ID = other.ID;
		name = other.name;
		lastname = other.lastname;
		age = other.age;
		gender = other.gender;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getLastname() {
		return lastname;
	}

	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}

	public void switchPerson(Person other) {
		String temp;
		int tempAge;
		temp = this.ID;
		this.ID = other.ID;
		other.ID = temp;
		
		temp = this.name;
		this.name = other.name;
		other.name = temp;
		
		temp = this.lastname;
		this.lastname = other.lastname;
		other.lastname = temp;
		
		temp = this.gender;
		this.gender = other.gender;
		other.gender = temp;
		
		tempAge = this.age;
		this.age = other.age;
		other.age = tempAge;
	}
	
	public Person clone() {
		Person temp = new Person(this.ID, this.name, this.lastname, this.gender, this.age);
		return temp;
	}
	
	public String toString() {
		return ID + ", " + name + " " + lastname + ", " + gender + ", " + age;
	}
}