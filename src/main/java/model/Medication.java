package model;

public class Medication {

	private boolean chlOrPoly;
	private boolean iodOrGau;
	private boolean glue;
	private boolean biopatch;
	
	public Medication(boolean chlOrPoly, boolean iodOrGau, boolean glue, boolean biopatch) {
		this.chlOrPoly=chlOrPoly;
		this.iodOrGau=iodOrGau;
		this.biopatch=biopatch;
		this.glue=glue;
	}
	
	public boolean getChlOrPoly() {
		return this.chlOrPoly;
	}
	
	public boolean getIodOrGau() {
		return this.iodOrGau;
	}
	
	public boolean getBioptach() {
		return this.biopatch;
	}
	
	public boolean getGlue() {
		return this.glue;
	}
	
}
