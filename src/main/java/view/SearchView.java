package view;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import main.Authentication;
import main.MyUI;
import model.User;

@SuppressWarnings("serial")

public class SearchView extends VerticalLayout implements View{

	private Label title = new Label("Ricerca dei CVC");

	private Button logout = new Button("Logout");
	private Button addP = new Button("Aggiungi Paziente");
	private Button stat = new Button("Statistiche");

	private Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
	protected User user = localAuth.getUser();
	
	public SearchView() {
		Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
		user = localAuth.getUser();
		VerticalLayout contentView = new VerticalLayout();
		new Navigator(UI.getCurrent(), contentView);
		Label auth = new Label("<p>Benvenuto <strong>"+user.getType().toUpperCase()+"</strong> "+user.getName()+"</p>");
		auth.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        UI.getCurrent().getNavigator().addView("", SearchView.class);
        UI.getCurrent().getNavigator().setErrorView(SearchView.class);
        UI.getCurrent().getNavigator().addView(StatisticView.NAME, StatisticView.class);
        UI.getCurrent().getNavigator().addView(CVCView.NAME, CVCView.class);
        UI.getCurrent().getNavigator().addView(AddScoreView.NAME, AddScoreView.class);

		logout.addClickListener(e -> doLogout());
		
		stat.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(StatisticView.NAME));
		
		addP.addClickListener(event -> addCVC());
		
		Component search = search();
		addComponents(auth, logout, title, search, addP, stat);
		setComponentAlignment(logout, Alignment.TOP_RIGHT);
		setComponentAlignment(auth, Alignment.TOP_RIGHT);
		setMargin(true);
		setSpacing(true);
	}
	
	private Component search() {
		HorizontalLayout search = new HorizontalLayout();
		search.setSizeUndefined();
		Panel pan = new Panel();
		pan.setSizeUndefined();
		TextField searchPatient = new TextField("Nome, Cognome o Codice Fiscale Paziente");
		searchPatient.setPlaceholder("Nome, Cognome o CF");
		Button searchButton = new Button("Ricerca Paziente");
		searchButton.addClickListener(event -> doSearch(searchPatient.getValue().trim()));
		search.addComponents(searchPatient, searchButton);
		searchButton.setClickShortcut(KeyCode.ENTER);
		search.setComponentAlignment(searchButton, Alignment.MIDDLE_RIGHT);
		search.setMargin(true);
		search.setSpacing(true);
		pan.setContent(search);
		return pan;
	}
	
	private void doSearch(String code) {
		if (code==null)
			code="";
		
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
