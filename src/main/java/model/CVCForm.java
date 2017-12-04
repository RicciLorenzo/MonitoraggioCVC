package model;

import java.util.Date;

public class CVCForm {

	private int idCVC;
	//private image patient label
	private String patientLabel;
	
	private int lumi;
	private int french;
	private float veinDiameter;
	
	private Patient patient;

	//fine ripetizioni
	private boolean insertionMode;
	private boolean difficultyInsertion;
	private boolean ecoguidedPositioning;
	private boolean chestRx;
	
	private Complication complication;
	
	private String typologyStructure;
	
	
	private String insertionSite;
	private String otherInsertion;
	private boolean tip;
	private String nWay;
	
	private Medication medication;
	
	private String sign;
	
	private String destinationOfPatient;
	private Date removalDate;
	
	private String motivation;
	private String otherMotivation;
	
	private boolean cvcTipCulture;
	private String cvcBloodCulture;
	private String otherCvcBloodCulture;
	private boolean transferWithCvc;
	private Date transferDate;
	private String transferLocation;
	private boolean cvcInfection;
	private boolean cvcBacteremia;
	
	private String fasteningType;
	private String otherFastening;
	
	
	public CVCForm(int idCVC) {
		this.idCVC = idCVC;
	} 
	
	
	
	
}
