package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.birthday=birthday;
		this.dateOfPlacement = dateOfPlacement;
		this.allergy = new Allergy(allergy0, aT);
	}
	
	public Patient(String fiscalCode, String name, String surname, LocalDate birthday, LocalDate dateOfPlacement, String allergy0, String allergy1, boolean aT, String Placement) {
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.birthday=birthday;
		this.dateOfPlacement = dateOfPlacement;
		this.allergy = new Allergy(allergy0, allergy1, aT);
	}	
	
	public String getFiscalCode() {
		return this.fiscalCode;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
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
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Date date=null;
		try {
			date = formatter.parse(dateOfPlacement.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return formatter.format(date);
	}
	
	public Allergy getAllergy() {
		return allergy;
	}
	
	public String getPlacement() {
		return placement;
	}
	
}
