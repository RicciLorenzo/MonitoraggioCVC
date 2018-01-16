package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import model.Patient;

public class CVCView extends VerticalLayout implements View{
	
	private Patient p;
	//Component patient = new PatientView(p);
	private Button back = new Button("belobelo");
	
	
	
	public CVCView() {
		setMargin(true);
		this.addComponent(back);
	}
	
	private Component PatientView(Patient p){
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
