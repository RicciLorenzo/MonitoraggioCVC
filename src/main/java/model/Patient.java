package model;

import java.util.Date;

public class Patient {

	private String patientLabel;
	private String fiscalCode;
	private String name;
	private String surname;
	private Date dateOfPlacement;
	private String allergy1;
	private String allergy2;
	private String allergy3;
	private boolean anticoagulantTheraphy;
	private String placementType;
	private String otherPlacement;
	
	public Patient(String patientLabel, String fiscalCode, String name, String surname, Date dateOfPlacement) {
		this.patientLabel = patientLabel;
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.dateOfPlacement = dateOfPlacement;
	}
	
	
	public String getPateintLabel() {
		return this.patientLabel;
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
	
	public Date getDateOfPlacement() {
		return this.dateOfPlacement;
	}
	
	
	
}
