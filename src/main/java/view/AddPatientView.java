package view;

import java.time.LocalDate;
import java.util.ArrayList;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import model.Allergy;

@SuppressWarnings("serial")

public class AddPatientView extends FormLayout implements View{
	
	public final static String Name = "ADD_PATIENT";

	private Label title = new Label("Aggiungi Paziente");
	private TextField name = new TextField("Nome");
	private TextField surname = new TextField("Cognome");
	private TextField fiscalCode = new TextField("Codice Fiscale");
	private DateField birthday = new DateField("Data di Nascita");
	private DateField placementDate = new DateField("Data Posizionamento", LocalDate.now());
	private CheckBox allergy1 = new CheckBox("Nessuna");
	private CheckBox allergy2 = new CheckBox("Clorexidina");
	private CheckBox allergy3 = new CheckBox("Iodio");
	private CheckBox anticoagulant = new CheckBox("Terapia Anticoagulante");
	private ArrayList<String> placementList = new ArrayList<String>();
	private NativeSelect<String> placement;
	private TextField otherP = new TextField("Altro Posizionamento");
	private Button back = new Button("Annulla");
	private Button save = new Button("Salva");
	private Button saveCVC = new Button("Salva e aggiungi CVC");
	
	AddPatientView() {
		
		System.out.println("add_patient");
		placementList.add("UTI");
		placementList.add("PS");
		placementList.add("SO");
		placementList.add("RADIOLOGIA");
		placementList.add("U.O. di ONCOLOGIA");
		placementList.add("HOSPICE");
		placementList.add("ALTRO");
		placement = new NativeSelect<String>("Posizionamento", placementList);
		placement.setEmptySelectionAllowed(false);
		back.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(""));
		setMargin(true);
		setSpacing(true);
		setSizeFull();
		addComponents(title, name, surname, fiscalCode, birthday, placementDate, new Label("Allergie"), allergy1, allergy2, allergy3,
				anticoagulant, placement, otherP, back, save, saveCVC);
		
		otherP.setEnabled(false);
		
		placement.addValueChangeListener(e -> enableP(placement.getValue()));
		
		save.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	
            		Allergy allergy;	
            		if(allergy2.getValue()&&allergy3.getValue()) {
            			allergy = new Allergy("clorexidina", "iodio", anticoagulant.getValue());
            			}
            		if(allergy2.getValue()) {
            			allergy = new Allergy("clorexidina", anticoagulant.getValue());
            		}
            		if(allergy3.getValue()) {
            			allergy = new Allergy("iodio", anticoagulant.getValue());
            		}
            		else {
            			allergy = new Allergy("nessuna", anticoagulant.getValue());
            		}
            		String place;
            		if(placement.getValue().toString().equals("ALTRO")) {
            			place=otherP.getValue();
            		}
            		else
            			place=placement.getValue().toString();
            			
            		if ( dao.PatientDao.addPatient(name.getValue(), surname.getValue(), fiscalCode.getValue(), birthday.getValue(), placementDate.getValue(), allergy, place) ) {
            				Notification notif = new Notification("PAZIENTE SALVATO", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            			}
            		else {
            			Notification notif = new Notification("DATI ERRATI, PAZIENTE NON SALVATO", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            			}
            		}
			});

		saveCVC.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	
            		Allergy allergy;	
            		if(allergy2.getValue()&&allergy3.getValue()) {
            			allergy = new Allergy("clorexidina", "iodio", anticoagulant.getValue());
            			}
            		if(allergy2.getValue()) {
            			allergy = new Allergy("clorexidina", anticoagulant.getValue());
            		}
            		if(allergy3.getValue()) {
            			allergy = new Allergy("iodio", anticoagulant.getValue());
            		}
            		else {
            			allergy = new Allergy("nessuna", anticoagulant.getValue());
            		}
            		String place;
            		if(placement.getValue().toString().equals("ALTRO")) {
            			place=otherP.getValue();
            		}
            		else
            			place=placement.getValue().toString();
            			
            		if ( dao.PatientDao.addPatient(name.getValue(), surname.getValue(), fiscalCode.getValue(), birthday.getValue(), placementDate.getValue(), allergy, place) || dao.PatientDao.patientExist(fiscalCode.getValue()) ) {
            				Notification notif = new Notification("PAZIENTE SALVATO", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            				UI.getCurrent().getNavigator().addView(AddCVCView.Name, AddCVCView.class);
            				UI.getCurrent().getNavigator().navigateTo(AddCVCView.Name +"/"+fiscalCode.getValue());
            			}
            		else {
            			Notification notif = new Notification("DATI ERRATI, PAZIENTE NON SALVATO", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            			}
            		}
			});
		

	}
	private void enableP(String value) {
		if(value.equalsIgnoreCase("altro")) {
			otherP.setEnabled(true);
		}
		else {
			otherP.setEnabled(false);
			otherP.clear();
		}
	}
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		UI.getCurrent().setContent(new AddPatientView());
		Page.getCurrent().reload();
	}
}
