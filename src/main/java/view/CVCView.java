package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import model.CVCForm;
import model.Patient;
import model.RemovalCVC;
import model.ScoreForm;

public class CVCView extends VerticalLayout implements View{
	
	private String cvcID = "";
	private Patient p;
	private CVCForm CVC;
	private ScoreForm score;
	private RemovalCVC rem;
	private Label title = new Label("Scheda Monitoraggio CVC");
	//Component patient = new PatientView(p);
	//private Button back = new Button("Indietro");
	//private Button addScore = new Button("Aggiungi Valutazione");
	private Label insM; //modalità inserimento
	private Label indD; //difficoltà inserimento
	private Label eco; //posizionamento ecoguidato
	private Label rx; //rx torace
	private Label vein; //diametro vena
	private Label lumi; //lumi ???
	private Label french; //french ???
	private Label compl; //complicanze sì/no
	private Label pres; //tipologia di presidio
	private Label insS; //sito di inserimento
	private Label tip; //punta
	private Label way; //n. vie
	private Label med; //medicazione
	private Label sign; //firma
	private Label scorL = new Label("Score di Valutazione");
	private Label remL = new Label("Rimozione CVC");
	
	public CVCView() {
		setSpacing(true);
		setMargin(true);
		this.addComponents(buildTop(), title, scorL);
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(scorL, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(remL, Alignment.MIDDLE_CENTER);
	}
	
	private Component buildTop() {
		HorizontalLayout lay = new HorizontalLayout();
		lay.setSizeFull();
		lay.setMargin(true);
		lay.setSpacing(true);
		Panel pan = new Panel();
		pan.setSizeFull();
		Button back = new Button("Indietro");
		Button addScore = new Button("Aggiungi Valutazione");
		lay.addComponents(back, addScore);
		lay.setComponentAlignment(back, Alignment.MIDDLE_LEFT);
		lay.setComponentAlignment(addScore, Alignment.MIDDLE_RIGHT);
		pan.setContent(lay);
		return pan;
	}
	
	private Component buildPatient(Patient p){
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	private Component buildCVC(String cvcID){
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	private Component buildScore(String cvcID){
		final VerticalLayout back = new VerticalLayout();
		Label date; //data score
		Label score; //score number
		Label wash; //lavaggio
		Label epa; //eparinizz.???
		Label sostI; //sostituzione set infusivo
		Label medS; //sostituizione medicazione
		Label med; //medicata con 
		
		return back;
	}
	private Component buildRemoval(String cvcID) {
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	private void back(String fc) {
		UI.getCurrent().getNavigator().navigateTo(SearchResultView.NAME + "/" + fc);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		UI.getCurrent().setContent(new CVCView());
	}
	
	
	
}
