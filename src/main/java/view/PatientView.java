package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import model.Patient;

@SuppressWarnings("serial")

public class PatientView extends VerticalLayout implements View{

	public final static String NAME = "PATIENT"; 
	private Label name;
	private Label surname;
	private Label fiscalCode;
	private Label birthday;
	private Label dateOfPlacement;
	private Label allergy1;
	private Label allergy2;
	private Label allergy3;
	private Label anticoagulant;
	private TextField placement;
	private Button save;
	
	public PatientView(String code) {
		Patient p = dao.PatientDao.getPatient(code);
		name = new Label("Nome: "+p.getName());
		surname = new Label("Cognome: "+p.getSurname());
		fiscalCode = new Label("Codice Fiscale: "+p.getFiscalCode());
		birthday = new Label("Data di Nascita: "+p.getBirthday());
		dateOfPlacement = new Label("Data Posizionamento: "+p.getDateOfPlacement());
		if(!(p.getAllergy().getA0().equals("nessuna")))
			allergy1 = new Label("Nessuna");

		allergy1 = new Label(p.getAllergy().getA0());
		if(!(p.getAllergy().getA1()==null))
			allergy2 = new Label(p.getAllergy().getA1());
		
		anticoagulant = new Label("Terapia anticoagulante: "+(p.getAllergy().getAT()?"Sì":"No"));
		placement = new TextField("Posizionamento in: ");
		save = new Button("Salva Modifiche");
		this.addComponents(new Label("Modifica Paziente"), name, surname ,fiscalCode, birthday ,dateOfPlacement);

		this.addComponents(new Label("Allergie:"), allergy1,allergy2 ,allergy2);
		this.addComponents( anticoagulant, placement, save);
		save.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            			
            		if ( dao.PatientDao.updatePlacement(placement.getValue(), p.getFiscalCode()) ) {
            				Notification notif = new Notification("PAZIENTE SALVATO", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            				UI.getCurrent().getNavigator().navigateTo("");
            			}
            		else {
            			Notification notif = new Notification("DATI ERRATI, POSIZIONAMENTO NON AGGIORNATO", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            			}
            		}
			});
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("patient view"+event.getParameters());
		UI.getCurrent().setContent(new PatientView(event.getParameters()));
	}
	
	
}
