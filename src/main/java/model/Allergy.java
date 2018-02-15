package model;

public class Allergy {

	private String allergy0="";
	private String allergy1="";
	private boolean anticoagulantTheraphy;
	
	public Allergy(String allergy0, boolean aT) {
		this.allergy0=allergy0;
		this.allergy1="";
		this.anticoagulantTheraphy=aT;
	}
	
	public Allergy(String allergy0, String allergy1, boolean aT) {
		this.allergy0=allergy0;
		this.allergy1=allergy1;
		this.anticoagulantTheraphy=aT;
	}
	
	public String getA0() {
		return this.allergy0;
	}
	
	public String getA1() {
		return this.allergy1;
	}
	
	public boolean getAT() {
		return this.anticoagulantTheraphy;
	}
	
}
