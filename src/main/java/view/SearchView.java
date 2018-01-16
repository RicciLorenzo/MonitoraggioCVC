package view;

import java.util.ArrayList;

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
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import main.Authentication;
import main.MyUI;


public class SearchView extends VerticalLayout implements View{

	private Label title = new Label("Ricerca dei CVC");
	private TextField searchPatient = new TextField("Codice Fiscale Paziente");
	private Button logout = new Button("Logout");
	private Button searchButton = new Button("Ricerca Paziente");
	private Button addP = new Button("Aggiungi Paziente");
	private Button stat = new Button("Statistiche");
	private ArrayList<String> result = new ArrayList<>();
	
	public SearchView() {
		
		VerticalLayout contentView = new VerticalLayout();
		new Navigator(UI.getCurrent(), contentView);
	
        UI.getCurrent().getNavigator().addView("", SearchView.class);
		
		logout.addClickListener(e -> doLogout());
		
		searchButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            		UI.getCurrent().getNavigator().navigateTo("" + "/" + searchPatient.getValue() );
            		System.out.println("ricerca???");
            }
        });
		
		addP.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            		UI.getCurrent().getNavigator().addView("addpatient", AddPatientView.class);
            		UI.getCurrent().getNavigator().navigateTo("addpatient");
            		System.out.println("aggiunta paziente???");
            }
        });
		
		Component search = search();
		addComponents(logout, title, search, addP, stat);
		setComponentAlignment(logout, Alignment.TOP_RIGHT);
		setMargin(true);
		setSpacing(true);
	}
	
	private Component search() {
		HorizontalLayout search = new HorizontalLayout();
		search.setSizeUndefined();
		Panel pan = new Panel();
		pan.setSizeUndefined();
		TextField searchPatient = new TextField("Codice Fiscale Paziente");
		Button searchButton = new Button("Ricerca Paziente");
		search.addComponents(searchPatient, searchButton);
		search.setComponentAlignment(searchButton, Alignment.MIDDLE_RIGHT);
		search.setMargin(true);
		search.setSpacing(true);
		pan.setContent(search);
		return pan;
	}
	
	private Component result() {
		GridLayout res = new GridLayout(6,2);
		res.setSizeUndefined();
		Panel pan = new Panel();
		pan.setSizeUndefined();
		
		Label name = new Label("Nome");
		Label surname = new Label("Cognome");
		Label date = new Label("Data di Nascita");
		Label fiscal = new Label("Codice Fiscale");
		Label ins = new Label("Inserimento");
		Label closed = new Label("Chiuso");
		
		Label nameT = new Label("Mario");
		Label surnameT = new Label("Rossi");
		Label birthT = new Label("17/09/1994");
		Label fiscalT = new Label("RSSMRI94P17M172G");
		Label insT = new Label("Succlavia DX");
		Label closedT = new Label("No");
		
		return res;
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
