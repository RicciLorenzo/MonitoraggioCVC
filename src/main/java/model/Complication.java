package model;

public class Complication {

	private boolean complication;
	private boolean hematoma;
	private boolean arteryPuncture;
	private boolean pnx;
	private String otherC;
	
	/**
	 * Crea un oggetto il cui campo segna la presenza o meno di complicanze, da usare prevalentemente se le complicanze sono assenti.
	 * @param complication
	 */
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
	/**
	 * Crea un oggetto per tener traccia delle complicanze al tempo di inserimento delcvc in caso di presenza.
	 * @param complication
	 * @param hematoma
	 * @param arteryPuncture
	 * @param pnx
	 * @param otherC stringa per un'altra complicanza da specificare
	 */
	public Complication(boolean complication, boolean hematoma, boolean arteryPuncture, boolean pnx, String otherC) {
		this.complication=complication;
		this.hematoma=hematoma;
		this.arteryPuncture=arteryPuncture;
		this.pnx=pnx;
		this.otherC=otherC;
	}
	
	/**
	 * 
	 * @return ritorna true se il paziente ha avuto complicanze
	 */
	public boolean hasComplication() {
		return this.complication;
	}
	
	/**
	 * 
	 * @return ritorna true se il paziente ha avuto un ematoma
	 */
	public boolean getHematoma() {
		return this.hematoma;
	}
	
	/**
	 * 
	 * @return ritorna true se il paziente ha avuto una puntura arteria
	 */
	public boolean getArtery() {
		return this.arteryPuncture;
	}
	
	/**
	 * 
	 * @return  ritorna true se il paziente ha avuto PNX
	 */
	public boolean getPnx() {
		return this.pnx;
	}
	
	/**
	 * 
	 * @return ritorna la stringa della complicanza aggiuntiva che non sia ematoma, puntura, pnx
	 */
	public String getOtherC() {
		return this.otherC;
	}
	
	
}
