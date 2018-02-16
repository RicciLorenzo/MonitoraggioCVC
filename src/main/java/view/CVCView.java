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

@SuppressWarnings("serial")

public class CVCView extends VerticalLayout implements View{
	
	public static final String NAME = "CVC";
	private int cvcID;
	private Patient p;

	private Label title = new Label("Scheda Monitoraggio CVC");
	private Label scorL = new Label("Score di Valutazione");
	private Label remL = new Label("Rimozione CVC");
	
	public CVCView(int id) {
		this.cvcID=id;
		
		p = dao.CVCDao.getCVC(id).getPatient();
	
		setSpacing(true);
		setMargin(true);
		this.addComponents(buildTop(), title, buildPatient(p), buildCVC(Integer.valueOf(id).toString()), scorL);
		
		if(dao.ScoreCVCDao.CVCScoreExist(cvcID)) {
			ArrayList<ScoreForm> scores = dao.ScoreCVCDao.getScoreCVC(cvcID);
			for(ScoreForm score: scores) {
				System.out.println(score.getDate());
			this.addComponent(buildScore(score));
			}
		}
		if(dao.RemovalCVCDao.CVCRemovalExist(id)) {
			
			this.addComponents(remL, buildRemoval(Integer.valueOf(id).toString()));
			this.setComponentAlignment(remL, Alignment.MIDDLE_CENTER);
		}
		
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(scorL, Alignment.MIDDLE_CENTER);
		
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
		Button addRemove = new Button("Aggiungi Rimozione");
		lay.addComponents(back, addScore, addRemove);
		if(dao.RemovalCVCDao.CVCRemovalExist(cvcID)) {
			addScore.setEnabled(false);
			addRemove.setEnabled(false);
		}
		back.addClickListener(e -> back(p.getFiscalCode()));
		addRemove.addClickListener(e -> goToRemove(cvcID));
		addScore.addClickListener(e -> goToScore(cvcID));
		lay.setComponentAlignment(back, Alignment.MIDDLE_LEFT);
		lay.setComponentAlignment(addScore, Alignment.MIDDLE_RIGHT);
		lay.setComponentAlignment(addRemove, Alignment.TOP_RIGHT);
		pan.setContent(lay);
		return pan;
	}
	
	private void goToRemove(int cvcID) {
		System.out.println(String.valueOf(cvcID));
		UI.getCurrent().getNavigator().addView(AddRemovalCVCView.NAME, new AddRemovalCVCView(String.valueOf(cvcID)));
		UI.getCurrent().getNavigator().navigateTo(AddRemovalCVCView.NAME+"/"+String.valueOf(cvcID));
	}

	private void goToScore(int cvcID) {
        UI.getCurrent().getNavigator().addView(AddScoreView.NAME, new AddScoreView(String.valueOf(cvcID)));
		UI.getCurrent().getNavigator().navigateTo(AddScoreView.NAME+"/"+String.valueOf(cvcID));
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
		Label allergy = new Label("Allergie note: "+p.getAllergy().getA0() +" "+ (p.getAllergy().getA1()==null?"":p.getAllergy().getA1()));
		Label anti = new Label("Terapia anticoagulante: "+ (p.getAllergy().getAT() ? "Sì":"No"));
		Label position = new Label("Posizionamento in: "+p.getPlacement());
		Button modify = new Button("Modifica");
		modify.addClickListener(e -> modifyPatient(p.getFiscalCode()));
		lay.addComponents(name, surname, birthday, fiscalCode, date, allergy, anti, position);
		pan.setContent(lay);
		return pan;
	}
	
	private void modifyPatient(String id) {
		UI.getCurrent().getNavigator().addView(PatientView.NAME, PatientView.class);
		UI.getCurrent().getNavigator().navigateTo(PatientView.NAME+"/"+id);
	}

	private Component buildCVC(String CVCid){
		VerticalLayout lay = new VerticalLayout();
		CVCForm cvc = dao.CVCDao.getCVC(cvcID);
		Panel pan = new Panel();
		Label insM = new Label("Modalità Inserimento: "+(cvc.getInsertion().getInsertionMode() ? "Urgente":"Programmato"));
		Label insD = new Label("Difficoltà Inserimento: "+(cvc.getInsertion().getdiffInsertion()?"Sì":"No"));
		Label eco = new Label("Poszionamento Ecoguidato: "+(cvc.getEco()?"Sì":"No"));
		Label rx = new Label("RX Torace: "+(cvc.getChest()?"Sì":"No"));
		Label compl;
		if(cvc.getComplication().hasComplication()) {
			
			String c="";
			boolean first = true;
			int i;
			for(i=0 ;i <4;i++) {
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
							if(!first) {
								c+=", Puntura arteria";
							}
							else {
								c+="Puntura arteria";
								first=false;
							}
							
						}
						else {
							c+="";
						}
						break;
					case 2:
						if(cvc.getComplication().getPnx()) {
							if(!first) {
								c+=", PNX (anche a distanza di qualche giorno)";
							}
							else {
								c+="PNX (anche a distanza di qualche giorno)";
								first=false;								
							}

						}
						else {
							c+="";
						}
						break;
						
					case 3:
						c+=(cvc.getComplication().getOtherC()==null?"":", "+cvc.getComplication().getOtherC());
						break;
				}
			}
			compl = new Label("Complicanze: "+c);
		}
		else {
			compl = new Label("Complicanze: Nessuna");
		}
		String cT="";
		if(cvc.getType().equalsIgnoreCase("cicc")||cvc.getType().equalsIgnoreCase("picc")||cvc.getType().equalsIgnoreCase("ficc")) {
			cT+=(cvc.getTunneled()?"Tunellizzato":"")+(cvc.getUncuffed()?", Cuffiato":"");
		}
		Label pres = new Label("Tipologia di Presidio: "+cvc.getType().toUpperCase()+" "+cT);
		
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
		
		lay.addComponents(insM, insD, eco, rx, compl, pres, ins, fiss, tip, way, med, dest, lum, french, vein, sign, closed);
		
		pan.setContent(lay);
		return pan;
	}
	
	private Component buildScore(ScoreForm score){
		VerticalLayout lay = new VerticalLayout();
		Panel pan = new Panel();
		//System.out.println("data score"+score.getDate());
		Label date = new Label("Data: "+score.getDate().toString() ); //data score
		Label scoreN = new Label("Score: "+score.getScore()); //score number
		Label wash = new Label("Lavaggio: "+(score.getWash()?"Sì":"No")); //lavaggio
		Label epa = new Label("Eparinizz.: "+(score.getEparinizz()?"Sì":"No")); //eparinizz.???
		Label sostI = new Label("Sostituzione Set Infusivo: "+(score.getSostInfusive()?"Sì":"No"));; //sostituzione set infusivo
		Label medS = new Label("Sostituzione Medicazione: "+score.getMedicationCause()); 
		Label med = new Label("Medicazione: "+(score.getMedication().getChlOrPoly()?"Clorexidina Alcolica, ":"Poliuretano, ")+(score.getMedication().getIodOrGau()?"Iodio":"Garza + cerotto")+(score.getMedication().getGlue()?", Colla":"")+(score.getMedication().getBioptach()?", Bioptach":"")); 
		Label diffInf = new Label("Difficoltà Infusione: "+(score.getDiffInfusion()?"Sì":"No"));
		Label diffAsp = new Label("Difficoltà Aspirazione: "+(score.getDiffAspiration()?"Sì":"No"));
		Label sospInf = new Label("Sospetta Infezione: "+(score.getSuspInfection()?"Sì":"No"));
		Label obs = new Label("Ostruzione: "+(score.getObstruction()?"Sì":"No"));
		Label emo = new Label("Emocultura da Catetere Venoso: "+score.getCvcBlood());
		Label sign = new Label("Firma: "+score.getSign());
		lay.addComponents(date, scoreN, wash, epa, sostI, medS, med, diffInf, diffAsp, sospInf, obs, emo, sign);
		
		pan.setContent(lay);
		return pan;
	}
	private Component buildRemoval(String cvcID) {
		VerticalLayout lay = new VerticalLayout();
		Panel pan = new Panel();
		RemovalCVC remove = dao.RemovalCVCDao.getRemovalCVC(Integer.valueOf(cvcID).intValue());
		Label date = new Label("Data Rimozione CVC: "+remove.getRemovalDate().toString());
		Label mot = new Label("Motivazione: "+remove.getMotivation());
		Label tip = new Label("Coltura punta CVC: "+(remove.getCVCTip()?"Sì":"No"));
		Label bact = new Label("Batteriemia da Catetere correlata: "+(remove.getCVCBact()?"Sì":"No"));
		Label closed = new Label("Chiuso: Sì");
		
		lay.addComponents(date, mot, tip, bact, closed);
		
		pan.setContent(lay);
		return pan;
	}
	
	private void back(String fc) {
		UI.getCurrent().getNavigator().navigateTo(SearchResultView.NAME + "/" + fc);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		UI.getCurrent().setContent(new CVCView(Integer.valueOf((event.getParameters())).intValue()));
	}
	
	
	
}
