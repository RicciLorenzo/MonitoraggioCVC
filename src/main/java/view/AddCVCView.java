package view;

import java.util.Arrays;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import main.Authentication;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import model.CVCForm;
import model.Complication;
import model.Insertion;
import model.Medication;
import model.Patient;
import model.User;

@SuppressWarnings("serial")

public class AddCVCView extends FormLayout implements View{
	
	public final static String NAME = "ADD_CVC";
	
	private Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
	private User user = localAuth.getUser();
	
	private Label title = new Label("Aggiungi CVC");
	private Label fc;
	private RadioButtonGroup<String> insertionM = new RadioButtonGroup<>("Modalità Inserimento", Arrays.asList("Urgente","Programmato"));
	private CheckBox insertionD = new CheckBox("Difficoltà Inserimento");
	private RadioButtonGroup<String> eco = new RadioButtonGroup<>("Posizionamento Ecoguidato", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> rx = new RadioButtonGroup<>("Rx Torace", Arrays.asList("Sì","No"));
	private CheckBox complication = new CheckBox("Complicanze");
	private CheckBox ema = new CheckBox("Ematoma");
	private CheckBox punct = new CheckBox("Puntura Arteria");
	private CheckBox pnx = new CheckBox("PNX");
	private CheckBox otherCompC = new CheckBox("Altro");
	private TextField otherCompT = new TextField("Specificare Altro");
	private NativeSelect<String> pres = new NativeSelect<>("Tipologia di Presidio", Arrays.asList("CICC","PICC", "FICC", "Midline", "Minimidline", "Port-A-Cath", "Broviac", "Quinton", "Tesio"));
	private RadioButtonGroup<String> tunn = new RadioButtonGroup<>("Tunnellizzato", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> cuff = new RadioButtonGroup<>("Cuffuiato", Arrays.asList("Sì","No"));
	private NativeSelect<String> ins = new NativeSelect<String>("Sito Inserimento", Arrays.asList("Succlavia", "Giugulare", "Braccio", "Altro"));
	private TextField otherIns = new TextField("Specificare Altro");
	private RadioButtonGroup<String> side = new RadioButtonGroup<>("Lato", Arrays.asList("Dx","Sx"));
	private NativeSelect<String> fis = new NativeSelect<String>("Fissaggio", Arrays.asList("Griplock", "Statlock", "Securacath", "Punti Sutura", "Altro"));
	private TextField otherFis = new TextField("Specificare Altro");
	private RadioButtonGroup<String> tip = new RadioButtonGroup<String>("Punta", Arrays.asList("Aperta","Chiusa"));
	private RadioButtonGroup<String> way = new RadioButtonGroup<>("N. Vie", Arrays.asList("1","2","3"));
	private RadioButtonGroup<String> med1 = new RadioButtonGroup<>("Medicazione", Arrays.asList("Clorexidina alcolica","Poliuretano"));
	private RadioButtonGroup<String> med2 = new RadioButtonGroup<>("", Arrays.asList("Iodio","Garza e cerotto"));
	private CheckBox glue = new CheckBox("Colla");
	private CheckBox biop = new CheckBox("Biopatch");
	private RadioButtonGroup<String> des1 = new RadioButtonGroup<>("Sede di Destinazione del Paziente", Arrays.asList("Domicilio","Ospedale", "U.O./Servizio"));
	private NativeSelect<String> des2 = new NativeSelect<String>("Reparto Osepdale", Arrays.asList("Chirurgia", "Intensivo"));
	private TextField vein = new TextField("Diametro Vena (mm)");
	private NativeSelect<Integer> lum = new NativeSelect<Integer>("Numero Lumi", Arrays.asList(1,2,3));
	private NativeSelect<Integer> fr = new NativeSelect<Integer>("French", Arrays.asList(1,2,3,4,5,6));

	private TextField sign = new TextField("Firma del Medico");
	private Button back = new Button("Indietro");
	private Button add = new Button("Aggiungi");

	
	public AddCVCView(){
		fc = new Label("");
		setMargin(true);
		vein.setMaxLength(4);
		pres.setEmptySelectionAllowed(false);
		ins.setEmptySelectionAllowed(false);
		fis.setEmptySelectionAllowed(false);
		way.setItemCaptionGenerator(item -> item + " " + ((item=="1")?"via":"vie"));
		lum.setEmptySelectionAllowed(false);
		fr.setEmptySelectionAllowed(false);
		otherCompT.setMaxLength(30);
		otherIns.setMaxLength(30);
		otherFis.setMaxLength(30);
		sign.setValue(user.getName()+" "+user.getSurname());
		addComponents(title, fc, insertionM, insertionD, eco, rx, complication, ema, punct, pnx, otherCompC, otherCompT, pres, tunn, cuff, ins, otherIns);
		addComponents(side, fis, otherFis, tip, way, med1, med2, glue, biop, des1, des2, vein, lum, fr, sign,add);
		sign.setEnabled(false);
		ema.setEnabled(false);
		punct.setEnabled(false);
		pnx.setEnabled(false);
		otherCompC.setEnabled(false);
		otherCompT.setEnabled(false);
		
		complication.addValueChangeListener(e -> enableComp(complication.getValue()));
		
		otherCompC.addValueChangeListener(e -> enableOtherComp(otherCompC.getValue()));

		tunn.setEnabled(false);
		cuff.setEnabled(false);
		
		pres.addValueChangeListener(e -> enableTC(pres.getValue()));
		
		otherIns.setEnabled(false);
		
		ins.addValueChangeListener(e -> enableIns(ins.getValue()));
		
		otherFis.setEnabled(false);
		
		fis.addValueChangeListener(e -> enableFis(fis.getValue()));
		
		add.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            		
            	if(checkFields()) {
            	Patient p = dao.PatientDao.getPatient(fc.getValue().replaceAll("Codice Fiscale: ", ""));
            	String insertion="";
            	if(String.valueOf(ins.getValue()).equals("Altro")) {
            		insertion=otherIns.getValue();
            	}
            	else {
            		insertion=ins.getValue();
            	}
            	Insertion insF = new Insertion(insertionM.getValue().equalsIgnoreCase("urgente"), insertionD.getValue(), insertion, (side.getValue().equals("DX")?true:false) ); 
            	Complication compl = null;
            	if(complication.getValue()) {
            		if(otherCompC.getValue()) {
            		compl = new Complication(true, ema.getValue(), punct.getValue(), pnx.getValue(), otherCompT.getValue());
            		}
            		else {
                		compl = new Complication(true, ema.getValue(), punct.getValue(), pnx.getValue());            			
            		}
            	}
            	else {
            		compl = new Complication(false);
            	}
            	Medication med = new Medication((med1.getValue().equalsIgnoreCase("Clorexidina alcolica")), (med2.getValue().equalsIgnoreCase("Iodio")), glue.getValue(), biop.getValue());
            	String fix = "";
            	if(fis.getValue().equalsIgnoreCase("altro")) {
            		fix=otherFis.getValue();
            	}
            	else {
            		fix=fis.getValue();
            	}
            	String destin="";
            	if(des1.getValue().equals("Ospedale")) {
            		destin=des2.getValue();
            	}
            	else {
            		destin=des1.getValue();
            	}
            	
            	
            	CVCForm cvc= new CVCForm(p, insF, (eco.getValue().equals("DX")?true:false), (rx.getValue().equals("Sì")?true:false), compl, pres.getValue(), (tunn.getValue().equals("Sì")?true:false), (cuff.getValue().equals("Sì")?true:false), med, lum.getValue(), fr.getValue(),
            			Float.parseFloat(vein.getValue().replace(',', '.')), (tip.getValue().equals("Aperta")?true:false), Integer.valueOf(way.getValue()).intValue(), fix, destin, sign.getValue());
 	
            		if ( dao.CVCDao.addCVC(cvc) ) {
            				Notification notif = new Notification("SCHEDA SALVATA", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            				UI.getCurrent().getNavigator().navigateTo("");
            			}
            		else {
            			Notification notif = new Notification("SCHEDA NON SALVATA", Notification.Type.TRAY_NOTIFICATION);
                		notif.setDelayMsec(1000);
                		notif.show(Page.getCurrent());
            			}
            	}
            	else {
        			Notification notif = new Notification("DATI MANCANTI", Notification.Type.TRAY_NOTIFICATION);
            		notif.setDelayMsec(1000);
            		notif.show(Page.getCurrent());           		
            	}
            		
            		
            		}
			});		
		
		
		
		back.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(""));
	}
	

	private boolean checkFields() {
		if(otherCompC.getValue()) {
			return !otherCompT.getValue().isEmpty();
		}
		if(!ins.isEmpty() && ins.getValue().equals("Altro")) {
			return !otherIns.getValue().isEmpty();
		}
		if(!fis.isEmpty() && fis.getValue().equals("Altro")) {
			return !otherFis.getValue().isEmpty();
		}
		
		boolean veinCheck=false;
		if(vein.isEmpty()) {
			return false;
		}
		
		else if(Float.parseFloat(vein.getValue().replace(',', '.'))>0.00&&Float.parseFloat(vein.getValue().replace(',', '.'))<=9.99)
			veinCheck=true;
		
		if(des1.isEmpty())
			return false;
		else if(des1.getValue().equalsIgnoreCase("ospedale")) {
			return !des2.isEmpty();
		}
		
		return !(insertionM.isEmpty()||eco.isEmpty()||rx.isEmpty()||pres.isEmpty()||ins.isEmpty()||side.isEmpty()||fis.isEmpty()||tip.isEmpty()||way.isEmpty()||med1.isEmpty()||med2.isEmpty()||des1.isEmpty()||vein.isEmpty()||!veinCheck||lum.isEmpty()||fr.isEmpty()||sign.getValue().isEmpty());
	}
	
	

	private void enableFis(String value) {
		if(value.equalsIgnoreCase("altro")) {
			otherFis.setEnabled(true);
		}
		else {
			otherFis.setEnabled(false);
			otherFis.clear();
		}
	}


	private void enableIns(String value) {
		if(value.equalsIgnoreCase("altro")) {
			otherIns.setEnabled(true);
		}
		else {
			otherIns.setEnabled(false);
			otherIns.clear();
		}
	}


	private void enableTC(String value) {
		if(value.equalsIgnoreCase("cicc") || value.equalsIgnoreCase("ficc") || value.equalsIgnoreCase("picc")) {
			tunn.setEnabled(true);
			cuff.setEnabled(true);
		}
		else {
			tunn.setEnabled(false);
			tunn.setValue("No");;
			cuff.setEnabled(false);
			cuff.setValue("No");;
		}
	}


	private void enableOtherComp(boolean state) {
		if(state) {
			otherCompT.setEnabled(true);
		}
		else {
			otherCompT.setEnabled(false);
			otherCompT.clear();
		}
	}


	public AddCVCView(String fiscalCode) {
		this();
		fc.setValue("Codice Fiscale: "+fiscalCode);

		}

	private void enableComp(boolean state) {
		if(state) {
			ema.setEnabled(true);
			punct.setEnabled(true);
			pnx.setEnabled(true);
			otherCompC.setEnabled(true);
		}
		else {
			ema.setEnabled(false);
			ema.clear();
			punct.setEnabled(false);
			punct.clear();
			pnx.setEnabled(false);
			pnx.clear();
			otherCompC.setEnabled(false);
			otherCompC.clear();
		}
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters().isEmpty())
			UI.getCurrent().setContent(new AddCVCView());
		else
			UI.getCurrent().setContent(new AddCVCView(event.getParameters()));
	}
	
}
