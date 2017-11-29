package view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class SearchView extends VerticalLayout{

	TextField searchPatient = new TextField("Codice Paziente");
	Button searchButton = new Button("Ricerca");
	
	public SearchView() {
		GridLayout grid = new GridLayout(3,2);
		
		setSizeFull();
		
		addComponent(searchPatient);
		addStyleName("searchview");
	}
	
}
