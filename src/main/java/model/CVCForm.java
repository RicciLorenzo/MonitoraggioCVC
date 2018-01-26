package model;

import java.util.ArrayList;
import java.util.Date;

public class CVCForm {

	private int idCVC;
	private String patientLabel;
	private Patient patient;
	private Insertion insertion;
	private boolean ecoguidedPositioning;
	private boolean chestRx;
	private Complication complication;
	private String type; //structure type
	private Medication medication;
	private String sign;
	private String destinationOfPatient;
	
	//check of cvc form
	private ArrayList<ScoreForm> scores;
	
	//added for medic
	private int lumi;
	private int french;
	private float veinDiameter;
	private boolean tip;
	private String nWay;
	private String fasteningType;
	private String otherFastening;
	
	public CVCForm(int idCVC) {
		this.idCVC = idCVC;
	} 
	
	
	
	
}
