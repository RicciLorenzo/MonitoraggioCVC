package view;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import main.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
@Theme("mytheme")

public class LoginView extends VerticalLayout{
	 
	TextField name = new TextField("Nome Utente");
	Label pass = new Label("Password");
	PasswordField psw = new PasswordField("Password");
	
    Label title = new Label(
            "<h1 style=\"text-align: center;\">\r\n" + 
            "<strong>Gestore Cateteri</strong></h1>");
    
    Button login = new Button("Login");
    
    Label authors = new Label("Emil Tomellini - Lorenzo Ricci");

    public LoginView() {
    		
    		addComponent(title);
        title.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        
        setComponentAlignment(title, Alignment.TOP_CENTER);
        
        addComponent(name);
        name.setPlaceholder("Nome Utente");
        setComponentAlignment(name, Alignment.TOP_CENTER);
        
        addComponent(psw);
        psw.setPlaceholder("Password");
        setComponentAlignment(psw, Alignment.TOP_CENTER);
        
        addComponent(login);
        setComponentAlignment(login, Alignment.TOP_CENTER);
        login.setClickShortcut(KeyCode.ENTER);
        
        login.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	if(name.getValue().isEmpty()) {
            		Notification notif = new Notification("INSERIRE NOME UTENTE", Notification.Type.TRAY_NOTIFICATION);
            		notif.setDelayMsec(1000);
            		notif.show(Page.getCurrent());
            		return;
            	}
            	if(psw.getValue().isEmpty()) {
            		Notification notif = new Notification("INSERIRE LA PASSWORD", Notification.Type.TRAY_NOTIFICATION);
            		notif.setDelayMsec(1000);
            		notif.show(Page.getCurrent());
            		return;
            	}
            	
            	Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
            	
            	if((localAuth.authenticate(name.getValue(), psw.getValue()))) {
            		if(localAuth.getUser().getType().equalsIgnoreCase("admin")) {
            			goToAddUserView();
            			System.out.println("Login utente admin");
            		}
            		else {
                		goToSearchView();
                		System.out.println("Login utente");
                		}
            	}
            	else {
            		Notification notif = new Notification("CREDENZIALI INVALIDE", Notification.Type.TRAY_NOTIFICATION);
            		notif.setDelayMsec(1000);
            		notif.show(Page.getCurrent());
            	}	

            }
        });
       
        addComponent(authors);
        setComponentAlignment(authors, Alignment.BOTTOM_RIGHT);
        
        setSizeFull();
        addStyleName("loginview");
    }
    
    private void goToSearchView() {
    		Page.getCurrent().setTitle("CVC ricerca");
    		UI.getCurrent().setContent(new SearchView());
    		removeStyleName("loginview");
    }
    
    private void goToAddUserView() {
    		Page.getCurrent().setTitle("ADMIN");
    		UI.getCurrent().setContent(new AddUserView());
    		removeStyleName("loginview");
    }
    
    
}
