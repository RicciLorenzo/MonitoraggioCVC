package view;

import java.time.LocalDate;
import java.util.Arrays;

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
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AddRemovalCVCView extends FormLayout implements View{
	
	private String formId;
	private DateField dateRemoval = new DateField("Data rimozione", LocalDate.now());
	private RadioButtonGroup<String> motivation = new RadioButtonGroup<>("Motivazione", Arrays.asList("Dislocazione", "Sospetta infezione", "Infezione da CVC", "Autorimozione", "Difficoltà aspirazione", "Sostituzione", "Decesso", "Altro"));
	private TextField otherMot = new TextField("Specificare altro");
	private RadioButtonGroup<String> col = new RadioButtonGroup<>("Coltura Punta CVC", Arrays.asList("Sì", "No"));
	private RadioButtonGroup<String> inf = new RadioButtonGroup<>("Batteriemia da catetere correlata", Arrays.asList("Sì", "No"));
	private CheckBox closed = new CheckBox("Chiusura");
	private Button add = new Button("Aggiungi");
	
	public AddRemovalCVCView(String formId) {
		setMargin(true);
		closed.setValue(true);
		addComponents(dateRemoval, motivation, otherMot, col, inf, closed,add);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new AddRemovalCVCView(event.getParameters()));
		
	}
	
}