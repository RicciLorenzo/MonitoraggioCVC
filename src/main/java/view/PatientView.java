package view;

import java.time.*;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import model.Patient;

public class PatientView extends VerticalLayout {

	private Label patientLabel;
	private Label name;
	private Label surname;
	private Label fiscalCode;
	private Image image;
	private Label dateOfPlacement;
	private Label allergy1;
	private Label allergy2;
	private Label allergy3;
	private CheckBox anticoagulant;
	private Label placement;
	private Label otherPlacement;
	
	public PatientView(Patient p) {
		patientLabel = new Label(p.getPateintLabel());
		name = new Label(p.getName());
		surname = new Label(p.getSurname());
		fiscalCode = new Label(p.getFiscalCode());
		//image
		dateOfPlacement = new Label(p.getDateOfPlacement());
		//allergy
		anticoagulant = new CheckBox("Terapia anticoagulante");
		anticoagulant.setReadOnly(true);
		placement = new Label(p.getPlacementType());
		if(p.getPlacementType().equalsIgnoreCase("altro"))
		{
			otherPlacement = new Label(p.getOtherPlacement());
		}
		
		
		
	}
	
	
}
