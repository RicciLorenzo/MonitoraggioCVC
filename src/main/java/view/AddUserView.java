package view;


import java.util.Arrays;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import main.MyUI;
import model.Encode;

@SuppressWarnings("serial")

public class AddUserView extends VerticalLayout implements View{

	Label title = new Label("Aggiungi Utente");
	TextField username= new TextField("Nome Utente");
	PasswordField password = new PasswordField("Password");
	PasswordField passwordCheck = new PasswordField("Password");
	TextField name = new TextField("Nome");
	TextField surname = new TextField("Cognome");
	NativeSelect<String> type = new NativeSelect<String>("Tipologia Utente", Arrays.asList("Medico", "Operatore", "Infermiere"));
	Button save = new Button("Aggiungi Utente");
	Button logout = new Button("Logout");
	
	public AddUserView() {
		
		setMargin(true);
		setSpacing(true);
		
		addComponents(title, username, password, passwordCheck, name, surname, type, save, logout);
		type.setEmptySelectionAllowed(false);
		type.setValue("Medico");
		logout.addClickListener(event -> doLogout());
		
		save.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {

            		if (username.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
            			Notification notif = new Notification("DATI MANCANTI", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            		}
            		else if(!(password.getValue().equals(passwordCheck.getValue()))) {
            			Notification notif = new Notification("PASSWORD NON UGUALI", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            		}
            		else if(dao.UserDAO.addUser(username.getValue(), Encode.cryptingString(password.getValue()), name.getValue(), surname.getValue(), type.getValue().toString())) {
            			Notification notif = new Notification("UTENTE AGGIUNTO", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            		}
            		else {
            			Notification notif = new Notification("NOME UTENTE GIÀ UTILIZZATO", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            		}	
            		}
			});
		
		
	}
	
	private void doLogout() {
		System.out.println("logout utente");
		((MyUI) UI.getCurrent()).userLoggedOut();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new AddUserView());	
	}

}
