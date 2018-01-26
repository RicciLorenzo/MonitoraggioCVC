package view;

import java.net.URI;
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
import model.User;


public class SearchView extends VerticalLayout implements View{

	private Label title = new Label("Ricerca dei CVC");
	private Button logout = new Button("Logout");
	private Button addP = new Button("Aggiungi Paziente");
	private Button stat = new Button("Statistiche");
	
	private Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
	protected User user = localAuth.getUser();
	
	public SearchView() {
		VerticalLayout contentView = new VerticalLayout();
		new Navigator(UI.getCurrent(), contentView);
        UI.getCurrent().getNavigator().addView("", SearchView.class);
        UI.getCurrent().getNavigator().setErrorView(SearchView.class);

		logout.addClickListener(e -> doLogout());
		
		
		
		addP.addClickListener(event -> addCVC());
		
		Component search = search();
		Component pt = result();
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
		searchButton.addClickListener(event -> doSearch(searchPatient.getValue()));
		search.addComponents(searchPatient, searchButton);
		search.setComponentAlignment(searchButton, Alignment.MIDDLE_RIGHT);
		search.setMargin(true);
		search.setSpacing(true);
		pan.setContent(search);
		return pan;
	}
	
	private Component result() {
		HorizontalLayout hor = new HorizontalLayout();
		GridLayout res = new GridLayout(6,2);
		res.setSizeUndefined();
		Panel pan = new Panel();
		Panel pan1 = new Panel();
		pan.setSizeUndefined();
		
		Label name = new Label("Nome");
		Label surname = new Label("Cognome");
		Label date = new Label("Data di Nascita");
		Label fiscal = new Label("Codice Fiscale");
		Label ins = new Label("Inserimento");
		Label closed = new Label("Chiuso");
		Button vis = new Button("Visualizza");
		
		Label nameT = new Label("Mario");
		Label surnameT = new Label("Rossi");
		Label birthT = new Label("17/09/1994");
		Label fiscalT = new Label("RSSMRI94P17M172G");
		Label insT = new Label("Succlavia DX");
		Label closedT = new Label("No");
		
		res.addComponents(name, surname, date, fiscal, ins, closed, nameT, surnameT, birthT, fiscalT, insT, closedT);
		
		res.setMargin(true);
		res.setSpacing(true);
		pan1.setContent(res);
		hor.addComponents(pan1, vis);
		
		hor.setMargin(true);
		hor.setSpacing(true);
		hor.setComponentAlignment(vis, Alignment.MIDDLE_CENTER);
		pan.setContent(hor);
		return pan;
	}
	
	private void doSearch(String code) {
		UI.getCurrent().getNavigator().addView(SearchResultView.NAME, new SearchResultView(code));
		UI.getCurrent().getNavigator().navigateTo(SearchResultView.NAME +"/" + code);
	}
	
	private void addCVC() {
		UI.getCurrent().getNavigator().addView(AddPatientView.Name, new AddPatientView());
		UI.getCurrent().getNavigator().navigateTo(AddPatientView.Name);
		System.out.println("aggiunta paziente???");
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
