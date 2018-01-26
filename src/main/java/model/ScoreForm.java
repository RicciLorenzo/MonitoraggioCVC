package model;

import java.util.Date;

public class ScoreForm {

	private Date date;
	private int score;
	private boolean wash;
	private boolean eparinizz;
	private boolean sostInfusive;
	//to change in enum ???
	private String medicationCause;
	private Medication medication;
	private boolean diffInfusion;
	private boolean diffAspiration;
	private boolean suspInfection;
	private boolean obstruction;
	private String cvcBlodd;
	private String sign;
	
	public ScoreForm(Date date, int score, boolean wash, boolean eparinizz, boolean sostInfusive, String medicationCause, Medication medication, String sign) {
		this.date=date;
		this.score=score;
		this.wash=wash;
		this.eparinizz=eparinizz;
		this.sostInfusive=sostInfusive;
		this.medicationCause=medicationCause;
		this.medication=medication;
		this.sign=sign;
	}

	public Date getDate() {
		return this.date;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean getWash() {
		return this.wash;
	}
	
	public boolean getEparinizz() {
		return this.eparinizz;
	}

	public boolean getSostInfusive() {
		return this.sostInfusive;
	}
	
	public String getMedicationCause() {
		return this.medicationCause;
	}
	
	public Medication getMedication() {
		return this.medication;
	}
	
	public String getSign() {
		return this.sign;
	}
	
}
