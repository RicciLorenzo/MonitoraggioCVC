package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import model.CVCForm;
import model.Patient;
import model.ScoreForm;

public class CVCView extends VerticalLayout implements View{
	
	private String cvcID = "";
	private Patient p;
	private CVCForm CVC;
	private ScoreForm score;
	private Label title = new Label("Scheda Monitoraggio CVC");
	//Component patient = new PatientView(p);
	private Button back = new Button("Indietro");
	private Button addScore = new Button("Aggiungi Valutazione");
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
	
	public CVCView() {
		
		setMargin(true);
		this.addComponent(back);
	}
	
	private Component buildPatient(Patient p){
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	private Component buildCVC(String cvcID){
		final VerticalLayout back = new VerticalLayout();
		
		return back;
	}
	
	private Component buildScore(Patient p){
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
