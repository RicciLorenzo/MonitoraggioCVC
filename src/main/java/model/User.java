package model;

public class User {
	
	private String name;
	private String surname;
	private UserType type;
	//private String fiscalCode;
	
	public User(String name, String surname, UserType type) {
		this.name = name;
		this.surname = surname;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	
}