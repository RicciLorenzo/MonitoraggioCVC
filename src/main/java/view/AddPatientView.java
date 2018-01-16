package view;

import java.time.LocalDate;
import java.util.ArrayList;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AddPatientView extends FormLayout implements View{

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
	private NativeSelect placement;
	private TextField otherP = new TextField("Altro Posizionamento");
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
		placement = new NativeSelect("Posizionamento", placementList);
		setMargin(true);
		setSpacing(true);
		setSizeFull();
		addComponents(title, name, surname, fiscalCode, birthday, placementDate, allergy1, allergy2, allergy3, anticoagulant, placement, otherP, save, saveCVC);

		//UI.getCurrent().setContent(vert);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		UI.getCurrent().setContent(new AddPatientView());
	}
}
