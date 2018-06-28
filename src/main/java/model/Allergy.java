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
	/**
	 * Crea un oggetto Allergy per tener traccia delle allergie di un paziente.
	 * @param allergy0 allergia 0
	 * @param allergy1 allergia 1
	 * @param aT terapia anticoagulante
	 */
	public Allergy(String allergy0, String allergy1, boolean aT) {
		this.allergy0=allergy0;
		this.allergy1=allergy1;
		this.anticoagulantTheraphy=aT;
	}
	/**
	 * 
	 * @return ritorna come stringa l'allergia 0
	 */
	public String getA0() {
		return this.allergy0;
	}
	/**
	 * 
	 * @return ritorna come stringa l'allergia 1 se assente ritorna una stringa vuota
	 */
	public String getA1() {
		return this.allergy1;
	}
	/**
	 * 
	 * @return ritorna il booleano se il paziente ha terapia anticoagulante
	 */
	public boolean getAT() {
		return this.anticoagulantTheraphy;
	}
	
}
