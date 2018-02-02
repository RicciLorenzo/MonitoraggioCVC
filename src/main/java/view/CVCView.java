package view;

import java.util.ArrayList;

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
	
	private int cvcID;
	private Patient p;
	private CVCForm CVC;

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
	
	public CVCView(int id) {
		this.cvcID=id;
		CVC = dao.CVCDao.getCVC(id);
		p = CVC.getPatient();
	
		setSpacing(true);
		setMargin(true);
		this.addComponents(buildTop(), title, buildPatient(p), scorL);
		
		if(dao.ScoreCVCDao.CVCScoreExist(cvcID)) {
			ArrayList<ScoreForm> scores = dao.ScoreCVCDao.getScoreCVC(cvcID);
			for(ScoreForm score: scores)
			this.addComponent(buildScore(score));
		}
		
		this.addComponent(scorL);
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
		back.addClickListener(e -> back(p.getFiscalCode()));
		lay.setComponentAlignment(back, Alignment.MIDDLE_LEFT);
		lay.setComponentAlignment(addScore, Alignment.MIDDLE_RIGHT);
		pan.setContent(lay);
		return pan;
	}
	
	private Component buildPatient(Patient p){
		VerticalLayout lay = new VerticalLayout();		
		Panel pan = new Panel();
		pan.setSizeUndefined();
		Label name = new Label("Nome: "+p.getName());
		Label surname = new Label("Cognome: "+p.getSurname());
		Label birthday = new Label("Data di Nascita: "+p.getBirthday());
		Label fiscalCode = new Label("Codice Fiscale: "+(p.getFiscalCode()));
		Label date = new Label("Data Posizionamento: "+p.getDateOfPlacement());
		Label allergy = new Label("Allergie note: "+p.getAllergy().getA0() +" "+ p.getAllergy().getA1());
		Label anti = new Label("Terapia anticoagulante: "+ (p.getAllergy().getAT() ? "Sì":"No"));
		Label position = new Label("Posizionamento in: "+p.getPlacement());
		Button modify = new Button("Modifica");
		lay.addComponents(name, surname, birthday, fiscalCode, date, allergy, anti, position);
		pan.setContent(lay);
		return pan;
	}
	
	private Component buildCVC(String CVCid){
		VerticalLayout lay = new VerticalLayout();
		CVCForm cvc = dao.CVCDao.getCVC(cvcID);
		Panel pan = new Panel();
		Label insM = new Label("Modalità Inserimento: "+(cvc.getInsertion().getInsertionMode() ? "Urgente":"Programmato"));
		Label insD = new Label("Difficoltà Inserimento: "+(cvc.getInsertion().getdiffInsertion()?"Sì":"No"));
		Label eco = new Label("Poszionamento Ecoguidato: "+(cvc.getEco()?"Sì":"No"));
		Label rx = new Label("RX Torace: "+(cvc.getChest()?"Sì":"No"));
		if(cvc.getComplication().hasComplication()) {
			
			String c="";
			boolean first = true;
			int i;
			for(i=0 ;i <4;i++) {
			if(first) {
				c+="";
			}
			else {
				c+=", ";
				}
				switch(i) {
					case 0:
						if(cvc.getComplication().getHematoma()) {
							c+="Ematoma";
							first=false;
						}
						else {
							c+="";
						}	
						break;
					case 1:
						if(cvc.getComplication().getArtery()) {
							c+="Puntura arteria";
							first=false;
						}
						else {
							c+="";
						}
						break;
					case 2:
						if(cvc.getComplication().getPnx()) {
							c+="PNX (anche a distanza di qualche giorno)";
							first=false;
						}
						else {
							c+="";
						}
						break;
					case 3:
						if(cvc.getComplication().getOtherC().equals("")) {
							c+="";
						}
						else {
							c+=cvc.getComplication().getOtherC();
						}
						break;
				}
			}
			Label compl = new Label("Complicanze: "+c);
		}
		else {
			Label compl = new Label("Complicanze: Nessuna");
		}
		String cT="";
		if(cvc.getType().equalsIgnoreCase("cicc")||cvc.getType().equalsIgnoreCase("picc")||cvc.getType().equalsIgnoreCase("ficc")) {
			cT+=cvc.getTunneled()+" "+cvc.getUncuffed();
		}
		Label pres = new Label("Tipologia di Presidio: "+cvc.getType()+" "+cT);
		
		Label ins = new Label("Sito Inserimento: "+cvc.getInsertion().getInsertionSite()+" "+(cvc.getInsertion().getInsertionSide()?"DX":"SX"));
		Label fiss = new Label("Fissaggio: "+cvc.getFastening());
		Label tip = new Label("Punta: "+(cvc.getTip()?"Aperta":"Chiusa"));
		Label way = new Label("N. vi"+((cvc.getWay()==1)?"a ":"e ")+cvc.getWay());
		Label med = new Label("Medicazione: "+(cvc.getMedication().getChlOrPoly()?"Clorexidina Alcolica, ":"Poliuretano, ")+(cvc.getMedication().getIodOrGau()?"Iodio":"Garza + cerotto")+(cvc.getMedication().getGlue()?", Colla":"")+(cvc.getMedication().getBioptach()?", Bioptach":""));
		Label dest = new Label("Sede di Destinazione del Paziente: "+cvc.getDestination());
		Label lum = new Label("Lumi: "+cvc.getLumi());
		Label french = new Label("French: "+cvc.getFrench());
		Label vein = new Label("Diametro vena: "+ cvc.getVeinDiameter()+"mm");
		Label sign = new Label("Firma: "+cvc.getSign());
		Label closed = new Label(dao.RemovalCVCDao.CVCRemovalExist(cvcID)?"Chiuso":"");
		
		//TO ADD COMPONENTS
		lay.addComponents();
		
		pan.setContent(lay);
		return pan;
	}
	
	private Component buildScore(ScoreForm score){
		VerticalLayout lay = new VerticalLayout();
		Panel pan = new Panel();
		Label date; //data score
		Label scoreN; //score number
		Label wash; //lavaggio
		Label epa; //eparinizz.???
		Label sostI; //sostituzione set infusivo
		Label medS; //sostituizione medicazione
		Label med; //medicata con 
		
		
		
		//TO ADD COMPONENTS
		lay.addComponents();
		
		pan.setContent(lay);
		return pan;
	}
	private Component buildRemoval(String cvcID) {
		VerticalLayout lay = new VerticalLayout();
		Panel pan = new Panel();

		
		
		//TO ADD COMPONENTS
		lay.addComponents();
		
		pan.setContent(lay);
		return pan;
	}
	
	private void back(String fc) {
		UI.getCurrent().getNavigator().navigateTo(SearchResultView.NAME + "/" + fc);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		UI.getCurrent().setContent(new CVCView(Integer.valueOf(event.getParameters().toString()).intValue()));
	}
	
	
	
}
