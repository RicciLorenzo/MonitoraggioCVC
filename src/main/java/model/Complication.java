package model;

public class Complication {

	private boolean complication;
	private boolean hematoma;
	private boolean arteryPuncture;
	private boolean pnx;
	private String otherC;
	
	public Complication(boolean complication) {
		this.complication=complication;
	}
	
	public Complication(boolean complication, boolean hematoma, boolean arteryPuncture, boolean pnx) {
		this.complication=complication;
		this.hematoma=hematoma;
		this.arteryPuncture=arteryPuncture;
		this.pnx=pnx;
		this.otherC="";
	}
	
	public Complication(boolean complication, boolean hematoma, boolean arteryPuncture, boolean pnx, String otherC) {
		this.complication=complication;
		this.hematoma=hematoma;
		this.arteryPuncture=arteryPuncture;
		this.pnx=pnx;
		this.otherC=otherC;
	}
	
	public boolean hasComplication() {
		return this.complication;
	}
	
	public boolean getHematoma() {
		return this.hematoma;
	}
	
	public boolean getArtery() {
		return this.arteryPuncture;
	}
	
	public boolean getPnx() {
		return this.pnx;
	}

	public String getOtherC() {
		return this.otherC;
	}
	
	
}
