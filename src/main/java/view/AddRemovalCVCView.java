package view;

import java.time.LocalDate;
import java.util.Arrays;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import main.Authentication;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import model.RemovalCVC;
import model.User;

@SuppressWarnings("serial")

public class AddRemovalCVCView extends FormLayout implements View{
	
	public static final String NAME = "REMOVE_CVC";
	
	private Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
	private User user = localAuth.getUser();
	
	private String id;
	private DateField dateRemoval = new DateField("Data rimozione", LocalDate.now());
	private RadioButtonGroup<String> motivation = new RadioButtonGroup<>("Motivazione", Arrays.asList("Dislocazione", "Sospetta infezione", "Infezione da CVC", "Autorimozione", "Difficoltà aspirazione", "Sostituzione", "Decesso", "Altro"));
	private TextField otherMot = new TextField("Specificare altro");
	private RadioButtonGroup<String> col = new RadioButtonGroup<>("Coltura Punta CVC", Arrays.asList("Sì", "No"));
	private RadioButtonGroup<String> inf = new RadioButtonGroup<>("Batteriemia da catetere correlata", Arrays.asList("Sì", "No"));
	private CheckBox closed = new CheckBox("Chiusura");
	private Button add = new Button("Aggiungi");
	
	public AddRemovalCVCView(String formId) {
		this.id=formId;
		setMargin(true);
		closed.setValue(true);
		addComponents(dateRemoval, motivation, otherMot, col, inf, closed,add);
		closed.setEnabled(false);
		otherMot.setEnabled(false);
		
		motivation.addValueChangeListener(e -> enableMot(motivation.getValue()));
		
		add.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
            	if(checkFields()) {
        		LocalDate date = dateRemoval.getValue();
        		String mot="";
        		if(motivation.getValue().equalsIgnoreCase("altro")) {
        			mot = otherMot.getValue();
        		}
        		else
        		{
        			mot = motivation.getValue();
        		}
        		boolean colt = col.getValue().equalsIgnoreCase("sì")?true:false;
        		boolean bact = inf.getValue().equalsIgnoreCase("sì")?true:false;
        		boolean clos = closed.getValue();
        		System.out.println("pane cvane"+Integer.valueOf(id).intValue());
        		System.out.println(mot);
        		RemovalCVC removal = new RemovalCVC(Integer.valueOf(id).intValue(), date, mot, colt, bact, clos);
            	    			
            		if ( dao.RemovalCVCDao.addRemovalCVC(removal) ) {
            				Notification notif = new Notification("RIMOZIONE SALVATA", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            				UI.getCurrent().getNavigator().navigateTo("");
            			}
            		else {
            			Notification notif = new Notification("RIMOZIONE NON SALVATA", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            			}
            		}
            	else {
            		Notification notif = new Notification("DATI MANCANTI", Notification.Type.TRAY_NOTIFICATION);
            		notif.setDelayMsec(1000);
            		notif.show(Page.getCurrent());
            	}
            }
			});
	}

	private boolean checkFields() {
		if(!motivation.isEmpty() && motivation.getValue().equals("Altro")) {
			return otherMot.getValue().isEmpty();
		}
		return !(dateRemoval.isEmpty()||motivation.getValue().isEmpty()||col.getValue().isEmpty()||inf.getValue().isEmpty());
	}
	
	private void enableMot(String value) {
		if(value.equalsIgnoreCase("altro")) {
			otherMot.setEnabled(true);
		}
		else {
			otherMot.setEnabled(false);
			otherMot.clear();
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("param"+event.getParameters());
		UI.getCurrent().setContent(new AddRemovalCVCView(event.getParameters().toString()));
		
	}
	
}