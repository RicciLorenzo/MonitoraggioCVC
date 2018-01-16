package view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import main.Authentication;
import main.MyUI;


public class SearchView extends VerticalLayout implements View{

	private TextField searchPatient = new TextField("Codice Paziente");
	private Button logout = new Button("Logout");
	private Button searchButton = new Button("Aggiunta Paziente");
	
	public SearchView() {

		//GridLayout grid = new GridLayout(3,2);
		setSpacing(false);
		setSizeFull();
		
		VerticalLayout contentView = new VerticalLayout();
        
		new Navigator(UI.getCurrent(), contentView);
	
        UI.getCurrent().getNavigator().addView("", SearchView.class);
		
		logout.addClickListener(e -> doLogout());
		
		searchButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            		UI.getCurrent().getNavigator().addView("addpatient", new AddPatientView());
            		UI.getCurrent().getNavigator().navigateTo("addpatient" + "/" + "bana" );
            		System.out.println("aggiunta paziente???");
          //  	UI.getCurrent().setContent(new AddPatientView());
            }
        });
		
		addComponents(logout, searchPatient, searchButton);
		setComponentAlignment(logout, Alignment.TOP_RIGHT);
		setComponentAlignment(searchPatient, Alignment.MIDDLE_LEFT);
		
		addStyleName("searchview");
	}
	
	private void doLogout() {
		System.out.println("logout utente");
		((MyUI) UI.getCurrent()).userLoggedOut();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new SearchView());
		
	}

	
	
}
