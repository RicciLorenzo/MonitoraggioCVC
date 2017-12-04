package model;

import java.util.ArrayList;

public class Allergy {

	private boolean allergy0;
	private boolean allergy1;
	private boolean anticoagulantTheraphy;
	
	public Allergy(boolean allergy0, boolean allergy1, boolean aT) {
		this.allergy0=allergy0;
		this.allergy1=allergy1;
		this.anticoagulantTheraphy=aT;
	}
	
	public boolean getA0() {
		return this.allergy0;
	}
	
	public boolean getA1() {
		return this.allergy1;
	}
	
	public boolean getAT() {
		return this.anticoagulantTheraphy;
	}
	
}
