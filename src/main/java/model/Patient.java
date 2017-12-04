package model;

import java.util.Date;

public class Patient {

	private String patientLabel;
	private String fiscalCode;
	private String name;
	private String surname;
	private Date dateOfPlacement;
	private Allergy allergy;
	private String placementType;
	private String otherPlacement;
	
	public Patient(String patientLabel, String fiscalCode, String name, String surname, Date dateOfPlacement, boolean allergy0, boolean allergy1, boolean aT) {
		this.patientLabel = patientLabel;
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.dateOfPlacement = dateOfPlacement;
		this.allergy = new Allergy(allergy0, allergy1, aT);
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
