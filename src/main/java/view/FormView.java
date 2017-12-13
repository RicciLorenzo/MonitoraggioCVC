package view;

import com.vaadin.ui.VerticalLayout;

import model.Patient;

public class FormView extends VerticalLayout{
	
	private Patient p;
	private PatientView pv = new PatientView(p);
	
	
}
