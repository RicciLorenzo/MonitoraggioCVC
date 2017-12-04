package model;

public class Medication {

	private boolean chlorhexidine;
	private boolean iodine;
	private boolean polyurethane;
	private boolean gauze;
	private boolean glue;
	
	public Medication(boolean chlorhexidine, boolean iodine, boolean polyurethane, boolean gauze, boolean glue) {
		this.chlorhexidine=chlorhexidine;
		this.iodine=iodine;
		this.polyurethane=polyurethane;
		this.gauze=gauze;
		this.glue=glue;
	}
	
	public boolean getClorhexidine() {
		return this.chlorhexidine;
	}
	
	public boolean getIodine() {
		return this.iodine;
	}
	
	public boolean getPoly() {
		return this.polyurethane;
	}
	
	public boolean getGauze() {
		return this.gauze;
	}
	
	public boolean getGlue() {
		return this.glue;
	}
	
}
