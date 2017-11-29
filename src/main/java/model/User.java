package model;

public class User {
	
	private String name;
	private String surname;
	private UserType type;
	
	public User(String name, String surname, UserType type) {
		this.name = name;
		this.surname = surname;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getType() {
		return type.toString();
	}
	
}