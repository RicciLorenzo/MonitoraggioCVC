package model;

public class CVCForm {

	private int idCVC;
	private Patient patient;
	private Insertion insertion;
	private boolean ecoguidedPositioning;
	private boolean chestRx;
	private Complication complication;
	private String type; //structure type
	private boolean tunneled;
	private boolean uncuffed;
	private Medication medication;
	private String sign;
	private String destinationOfPatient;
		
	//added for medic
	private int lumi;
	private int french;
	private float veinDiameter;
	private boolean tip;
	private int nWay;
	private String fasteningType;

	/**
	 * Da usare per creare un oggetto senza id che verrà usato per l'inserimento nel DB
	 * @param patient 
	 * @param insertion
	 * @param eco
	 * @param chest
	 * @param complication
	 * @param type
	 * @param tunneled
	 * @param uncuffed
	 * @param medication
	 * @param lumi
	 * @param french
	 * @param vein
	 * @param tip
	 * @param way
	 * @param fastening
	 * @param destination
	 * @param sign
	 */
	public CVCForm(Patient patient, Insertion insertion, boolean eco, boolean chest, 
			Complication complication, String type, boolean tunneled, boolean uncuffed, Medication medication, int lumi, int french, float vein, boolean tip,
			int way, String fastening, String destination, String sign ) {
		
		this.patient = patient;
		this.insertion = insertion;
		this.ecoguidedPositioning = eco;
		this.chestRx = chest;
		this.complication = complication;
		this.type = type;
		this.tunneled=tunneled;
		this.uncuffed=uncuffed;
		this.medication = medication;
		this.lumi = lumi;
		this.french = french;
		this.veinDiameter = vein;
		this.tip = tip;
		this.nWay = way;
		this.fasteningType = fastening;
		this.destinationOfPatient = destination;
		this.sign = sign;
	} 
	
	/**
	 * Da usare per recuperare la scheda cvc dal DB
	 * @param idCVC
	 * @param patient
	 * @param insertion
	 * @param eco
	 * @param chest
	 * @param complication
	 * @param type
	 * @param tunneled
	 * @param uncuffed
	 * @param medication
	 * @param lumi
	 * @param french
	 * @param vein
	 * @param tip
	 * @param way
	 * @param fastening
	 * @param destination
	 * @param sign
	 */
	public CVCForm(int idCVC, Patient patient, Insertion insertion, boolean eco, boolean chest, 
			Complication complication, String type, boolean tunneled, boolean uncuffed, Medication medication, int lumi, int french, float vein, boolean tip,
			int way, String fastening, String destination, String sign ) {
		
		this.idCVC = idCVC;
		this.patient = patient;
		this.insertion = insertion;
		this.ecoguidedPositioning = eco;
		this.chestRx = chest;
		this.complication = complication;
		this.type = type;
		this.tunneled=tunneled;
		this.uncuffed=uncuffed;
		this.medication = medication;
		this.lumi = lumi;
		this.french = french;
		this.veinDiameter = vein;
		this.tip = tip;
		this.nWay = way;
		this.fasteningType = fastening;
		this.destinationOfPatient = destination;
		this.sign = sign;
	} 
	
	public int getId() {
		return (int) Math.abs(this.patient.getFiscalCode().hashCode()^Long.valueOf(System.currentTimeMillis()).hashCode());
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public Insertion getInsertion() {
		return this.insertion;
	}
	
	public boolean getEco() {
		return this.ecoguidedPositioning;
	}
	
	public boolean getChest() {
		return this.chestRx;
	}
	
	public Complication getComplication() {
		return this.complication;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean getTunneled() {
		return this.tunneled;
	}
	
	public boolean getUncuffed() {
		return this.uncuffed;
	}

	public Medication getMedication() {
		return this.medication;
	}
	
	public int getLumi() {
		return this.lumi;
	}
	
	public int getFrench() {
		return this.french;
	}
	
	public float getVeinDiameter() {
		return this.veinDiameter;
	}
	
	public boolean getTip() {
		return this.tip;
	}
	
	public int getWay() {
		return this.nWay;
	}
	
	public String getFastening() {
		return this.fasteningType;
	}
	
	public String getDestination() {
		return this.destinationOfPatient;
	}
	
	public String getSign() {
		return this.sign;
	}
	
}
