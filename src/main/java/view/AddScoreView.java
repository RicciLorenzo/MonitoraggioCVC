package view;

import java.time.LocalDate;
import java.util.Arrays;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class AddScoreView extends FormLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 
	 * TO USE
	 * The options are normally laid out vertically.
	 * You can switch to horizontal layout by using the style name ValoTheme.OPTIONGROUP_HORIZONTAL with addStyleName().
	 * */ 
	private String cvcId;
	private Label title = new Label("Aggingi valutazione CVC");
	private DateField date = new DateField("Data", LocalDate.now());
	private Label score0 = new Label("Cute sana, integra, senza segni di flogosi");
	private Label score1 = new Label("Iperemia al punto di uscita del CVC < 1cm con o senza fibrina");
	private Label score2 = new Label("Iperemia al punto di uscita del CVC compresa tra 1 e 2cm con o senza fibrina");
	private Label score3 = new Label("Iperemia, secrezioni e/o pus, con o senza fibrina");
	private Label score4 = new Label("Eritema, tumefazione/indurimento del tratto sottocutaneo del CVC > a 2cm dal sito d'uscita");
	private Label score5 = new Label("Eritema/necrosi della cute al di sopra della camera del Port o presenza di essudato");
	private RadioButtonGroup<Integer> score = new RadioButtonGroup<>("Score", Arrays.asList(0,1,2,3,4,5));
	private RadioButtonGroup<String> wash = new RadioButtonGroup<>("Lavaggio", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> epa = new RadioButtonGroup<>("Eparinizz.", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> inf = new RadioButtonGroup<>("Sostituzione Set Infusivos", Arrays.asList("Sì","No"));
	private NativeSelect<String> sostMed = new NativeSelect<>("Sostituzione Medicazione", Arrays.asList("24/48h", "7 giorni", "Bagnata", "Sporca", "Staccata", "Dolore", "Febbre"));
	private RadioButtonGroup<String> med1 = new RadioButtonGroup<>("Medicata con:", Arrays.asList("Clorexidina alcolica","Poliuretano"));
	private RadioButtonGroup<String> med2 = new RadioButtonGroup<>("", Arrays.asList("Iodio","Garza e cerotto"));
	private CheckBox glue = new CheckBox("Colla");
	private CheckBox biop = new CheckBox("Biopatch");
	private RadioButtonGroup<String> emCVC = new RadioButtonGroup<>("Emocultura da CVC", Arrays.asList("Sì","No", "Altro"));
	private TextField otherEm = new TextField("Specificare Altro");
	private TextField sign = new TextField("Firma");
	private Button add = new Button("Aggiungi");
	
	public AddScoreView(String id) {
		this.cvcId=id;
		setMargin(true);
		sostMed.setEmptySelectionAllowed(false);
		score.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		addComponents(title,date,score0,score1,score2,score3,score4,score5,score,wash,epa,inf,sostMed,med1,med2,glue,biop,emCVC,otherEm,sign,add);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new AddScoreView(event.getParameters()));
		
	}

}
