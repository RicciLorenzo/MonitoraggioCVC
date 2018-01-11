package view;

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

public class AddCVCView extends FormLayout implements View{

	private Label title = new Label("Aggiungi CVC");
	private TextField fc = new TextField("Codice Fiscale Paziente");
	private RadioButtonGroup<String> insertionM = new RadioButtonGroup<>("Modalità Inserimento", Arrays.asList("Urgente","Programmato"));
	private CheckBox insertionD = new CheckBox("Difficoltà Inserimento");
	private RadioButtonGroup<String> eco = new RadioButtonGroup<>("Posizionamento Ecoguidato", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> rx = new RadioButtonGroup<>("Rx Torace", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> complication = new RadioButtonGroup<>("Complicanze", Arrays.asList("Sì","No"));
	private CheckBox ema = new CheckBox("Ematoma");
	private CheckBox punct = new CheckBox("Puntura Arteria");
	private CheckBox pnx = new CheckBox("PNX");
	private CheckBox otherCompC = new CheckBox("Altro");
	private TextField otherCompT = new TextField("Specificare Altro");
	private NativeSelect pres = new NativeSelect("Tipologia di Presidio", Arrays.asList("CICC","PICC", "FICC", "Midline", "Minimidline", "Port-A-Cath", "Broviac", "Quinton", "Tesio"));
	private RadioButtonGroup<String> tunn = new RadioButtonGroup<>("Tunnellizzato", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> cuff = new RadioButtonGroup<>("Cuffuiato", Arrays.asList("Sì","No"));
	private NativeSelect ins = new NativeSelect("Sito Inserimento", Arrays.asList("Succlavia", "Guigulare", "Braccio", "Altro"));
	private TextField otherIns = new TextField("Specificare Altro");
	private RadioButtonGroup<String> side = new RadioButtonGroup<>("Lato", Arrays.asList("Dx","Sx"));
	private NativeSelect fis = new NativeSelect("Fissaggio", Arrays.asList("Griplock", "Statlock", "Securacath", "Punti Sutura", "Altro"));
	private TextField otherFis = new TextField("Specificare Altro");
	private RadioButtonGroup<String> tip = new RadioButtonGroup<>("Punta", Arrays.asList("Aperta","Chiusa"));
	private RadioButtonGroup<String> way = new RadioButtonGroup<>("N. Vie", Arrays.asList("1","2","3"));
	private RadioButtonGroup<String> med1 = new RadioButtonGroup<>("Medicazione", Arrays.asList("Clorexidina alcolica","Poliuretano"));
	private RadioButtonGroup<String> med2 = new RadioButtonGroup<>("", Arrays.asList("Iodio","Garza e cerotto"));
	private CheckBox glue = new CheckBox("Colla");
	private CheckBox biop = new CheckBox("Biopatch");
	private RadioButtonGroup<String> des1 = new RadioButtonGroup<>("Sede di Destinazione del Paziente", Arrays.asList("Domicilio","Ospedale", "U.O./Servizio"));
	private NativeSelect des2 = new NativeSelect("Reparto Osepdale", Arrays.asList("Chirurgia", "Intensivo"));
	private TextField vein = new TextField("Diametro Vena (mm)");
	private NativeSelect lum = new NativeSelect("Numero Lumi", Arrays.asList(1,2,3));
	private NativeSelect fr = new NativeSelect("French", Arrays.asList(1,2,3,4,5,6));

	private TextField sign = new TextField("Firma del Medico");
	private Button add = new Button("Aggiungi");

	
	public AddCVCView(){
		setMargin(true);
		pres.setEmptySelectionAllowed(false);
		ins.setEmptySelectionAllowed(false);
		fis.setEmptySelectionAllowed(false);
		way.setItemCaptionGenerator(item -> item + " " + ((item=="1")?"via":"vie"));
		des2.setEmptySelectionAllowed(false);
		lum.setEmptySelectionAllowed(false);
		fr.setEmptySelectionAllowed(false);
		addComponents(title, fc, insertionM, insertionD, eco, rx, complication, ema, punct, pnx, otherCompC, otherCompT, pres, tunn, cuff, ins, otherIns,
        		side, fis, otherFis, tip, way, med1, med2, glue, biop, des1, des2, vein, lum, fr, sign,add);
	}
	
	
	public AddCVCView(String fiscalCode) {
		this();
		fc.setValue(fiscalCode);
		}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
