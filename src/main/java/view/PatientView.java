package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import model.Patient;

@SuppressWarnings("serial")

public class PatientView extends VerticalLayout implements View{

	private Label name;
	private Label surname;
	private Label fiscalCode;
	private Label dateOfPlacement;
	private Label allergy1;
	private Label allergy2;
	private Label allergy3;
	private CheckBox anticoagulant;
	private Label placement;	
	
	public PatientView(Patient p) {
		name = new Label(p.getName());
		surname = new Label(p.getSurname());
		fiscalCode = new Label(p.getFiscalCode());
		//image
		dateOfPlacement = new Label(p.getDateOfPlacement());
		if(!(p.getAllergy().getA0().equals("nessuna")))
			allergy1 = new Label("Nessuna");

		allergy1 = new Label(p.getAllergy().getA0());
		if(!(p.getAllergy().getA1().isEmpty()))
			allergy2 = new Label(p.getAllergy().getA1());
		
		anticoagulant = new CheckBox("Terapia anticoagulante");
		anticoagulant.setReadOnly(true);
		placement = new Label(p.getPlacement());

		addComponents(name, surname, fiscalCode, dateOfPlacement, allergy1, allergy2, allergy3, anticoagulant, placement);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Patient p = dao.PatientDao.getPatient(event.getParameters());
		UI.getCurrent().setContent(new PatientView(p));
	}
	
	
}
