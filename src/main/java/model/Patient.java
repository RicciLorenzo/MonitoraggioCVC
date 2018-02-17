package model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Patient {

	private String fiscalCode;
	private String name;
	private String surname;
	private LocalDate birthday;
	private LocalDate dateOfPlacement;
	private Allergy allergy;
	private String placement;
	
	public Patient(String fiscalCode, String name, String surname, LocalDate birthday, LocalDate dateOfPlacement, String allergy0, boolean aT, String Placement) {
		System.out.println("cane");
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.birthday=birthday;
		this.dateOfPlacement = dateOfPlacement;
		this.allergy = new Allergy(allergy0, aT);
		this.placement=Placement;
	}
	
	public Patient(String fiscalCode, String name, String surname, LocalDate birthday, LocalDate dateOfPlacement, String allergy0, String allergy1, boolean aT, String Placement) {
		System.out.println("cane");
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.birthday=birthday;
		this.dateOfPlacement = dateOfPlacement;
		this.allergy = new Allergy(allergy0, allergy1, aT);
		this.placement=Placement;
	}	
	
	public String getFiscalCode() {
		return this.fiscalCode;
	}
	
	public String getName() {
		return this.name.replace('_', ' ');
	}
	
	public String getSurname() {
		return this.surname.replace('_', ' ');
	}
	
	public String getBirthday() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			 return birthday.format(dtf);
		} catch (java.time.format.DateTimeParseException e) {
		   e.printStackTrace();
		}
		return "";
	}
	
	public String getDateOfPlacement() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			 return dateOfPlacement.format(dtf);
		} catch (java.time.format.DateTimeParseException e) {
		   e.printStackTrace();
		}
		return "";
	}
	
	public Allergy getAllergy() {
		return allergy;
	}
	
	public String getPlacement() {
		return placement;
	}

	public LocalDate getBirthdayDate() {
		return this.birthday;
	}
	
}
