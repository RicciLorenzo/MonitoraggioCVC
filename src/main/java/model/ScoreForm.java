package model;

import java.time.LocalDate;

public class ScoreForm {
	
	private int idScore;
	private int idCVC;
	private LocalDate date;
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
	private String cvcBlood;
	private String sign;
	
	public ScoreForm(LocalDate date, int score, boolean wash, boolean eparinizz, boolean sostInfusive, String medicationCause,
			Medication medication, boolean diffInfusion, boolean diffAspiration, boolean suspInfection, boolean obstruction, String cvcBlood, String sign) {
		this.date=date;
		this.score=score;
		this.wash=wash;
		this.eparinizz=eparinizz;
		this.sostInfusive=sostInfusive;
		this.medicationCause=medicationCause;
		this.medication=medication;
		this.diffInfusion=diffInfusion;
		this.diffAspiration=diffAspiration;
		this.suspInfection=suspInfection;
		this.obstruction=obstruction;
		this.cvcBlood=cvcBlood;
		this.sign=sign;
	}
	
	public ScoreForm(int idScore, LocalDate date, int score, boolean wash, boolean eparinizz, boolean sostInfusive, String medicationCause,
			Medication medication, boolean diffInfusion, boolean diffAspiration, boolean suspInfection, boolean obstruction, String cvcBlood, String sign) {
		this.idScore=idScore;
		this.date=date;
		this.score=score;
		this.wash=wash;
		this.eparinizz=eparinizz;
		this.sostInfusive=sostInfusive;
		this.medicationCause=medicationCause;
		this.medication=medication;
		this.diffInfusion=diffInfusion;
		this.diffAspiration=diffAspiration;
		this.suspInfection=suspInfection;
		this.obstruction=obstruction;
		this.cvcBlood=cvcBlood;
		this.sign=sign;
	}

	public ScoreForm(int idScore, int idCVC,LocalDate date, int score, boolean wash, boolean eparinizz, boolean sostInfusive, String medicationCause,
			Medication medication, boolean diffInfusion, boolean diffAspiration, boolean suspInfection, boolean obstruction, String cvcBlood, String sign) {
		this(idScore, date, score, wash, eparinizz, sostInfusive, medicationCause, medication, diffInfusion, diffAspiration, suspInfection, obstruction, cvcBlood, sign);
		this.idCVC=idCVC;
	}
	
	public int getIdCVC() {
		return this.idCVC;
	}
	
	public LocalDate getDate() {
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
	
	public boolean getDiffInfusion() {
		return this.diffInfusion;
	}
	
	public boolean getDiffAspiration() {
		return this.diffAspiration;
	}
	
	public boolean getSuspInfection() {
		return this.suspInfection;
	}
	
	public boolean getObstruction() {
		return this.obstruction;
	}
	
	public String getCvcBlood() {
		return this.cvcBlood;
	}
	
	public String getSign() {
		return this.sign;
	}
	
}
