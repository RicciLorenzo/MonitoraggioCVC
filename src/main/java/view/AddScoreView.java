package view;

import java.time.LocalDate;
import java.util.Arrays;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import main.Authentication;
import model.Medication;
import model.ScoreForm;
import model.User;

@SuppressWarnings("serial")

public class AddScoreView extends FormLayout implements View{

	public static final String NAME = "ADD_SCORE";
	
	private Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
	private User user = localAuth.getUser();
	
	private String cvcId;
	private Label title = new Label("Aggingi valutazione CVC");
	private DateField date = new DateField("Data", LocalDate.now());
	//TODO set image dimension
	private Image img0 = new Image("Score 0", new ThemeResource("img/score0.png"));
	private Label score0 = new Label("0 -Cute sana, integra, senza segni di flogosi");
	private Image img1 = new Image("Score 1", new ThemeResource("img/score1.png"));
	private Label score1 = new Label("1 -Iperemia al punto di uscita del CVC < 1cm con o senza fibrina");
	private Image img2 = new Image("Score 2", new ThemeResource("img/score2.png"));
	private Label score2 = new Label("2 -Iperemia al punto di uscita del CVC compresa tra 1 e 2cm con o senza fibrina");
	private Image img3 = new Image("Score 3", new ThemeResource("img/score3.png"));
	private Label score3 = new Label("3 -Iperemia, secrezioni e/o pus, con o senza fibrina");
	private Label score4 = new Label("4 -Eritema, tumefazione/indurimento del tratto sottocutaneo del CVC > a 2cm dal sito d'uscita");
	private Label score5 = new Label("5 -Eritema/necrosi della cute al di sopra della camera del Port o presenza di essudato");
	private RadioButtonGroup<Integer> score = new RadioButtonGroup<>("Score", Arrays.asList(0,1,2,3,4,5));
	private RadioButtonGroup<String> wash = new RadioButtonGroup<>("Lavaggio", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> epa = new RadioButtonGroup<>("Eparinizz.", Arrays.asList("Sì","No"));
	private RadioButtonGroup<String> inf = new RadioButtonGroup<>("Sostituzione Set Infusivos", Arrays.asList("Sì","No"));
	private NativeSelect<String> sostMed = new NativeSelect<>("Sostituzione Medicazione", Arrays.asList("24/48h", "7 giorni", "Bagnata", "Sporca", "Staccata", "Dolore", "Febbre"));
	private RadioButtonGroup<String> med1 = new RadioButtonGroup<>("Medicata con:", Arrays.asList("Clorexidina alcolica","Poliuretano"));
	private RadioButtonGroup<String> med2 = new RadioButtonGroup<>("", Arrays.asList("Iodio","Garza e cerotto"));
	private CheckBox glue = new CheckBox("Colla");
	private CheckBox biop = new CheckBox("Biopatch");
	private CheckBox diffInf = new CheckBox("Difficoltà Infusione");
	private CheckBox diffAsp = new CheckBox("Difficoltà Aspirazione");
	private CheckBox sospInf = new CheckBox("Sospetta Infezione");
	private CheckBox obs = new CheckBox("Ostruzione");
	private RadioButtonGroup<String> emCVC = new RadioButtonGroup<>("Emocultura da CVC", Arrays.asList("Sì","No", "Altro"));
	private TextField otherEm = new TextField("Specificare Altro");
	private TextField sign = new TextField("Firma");
	private Button add = new Button("Aggiungi");
	
	public AddScoreView(String id) {
		Page.getCurrent().setTitle("Aggiungi Valutazione");
		this.cvcId=id;
		setMargin(true);
		sostMed.setEmptySelectionAllowed(false);
		score.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		sign.setValue(user.getName()+" "+user.getSurname());
		otherEm.setMaxLength(30);
		img0.setHeight(100, Unit.PIXELS);
		img0.setWidth(100, Unit.PIXELS);
		img1.setHeight(100, Unit.PIXELS);
		img1.setWidth(100, Unit.PIXELS);
		img2.setHeight(100, Unit.PIXELS);
		img2.setWidth(100, Unit.PIXELS);
		img3.setHeight(100, Unit.PIXELS);
		img3.setWidth(100, Unit.PIXELS);
		addComponents(title,date,img0,score0,img1,score1,img2,score2,img3,score3,score4,score5,score,wash,epa,inf,sostMed,med1,med2,glue,biop,diffInf,diffAsp,sospInf,obs,emCVC,otherEm,sign,add);
		sign.setEnabled(false);
		otherEm.setEnabled(false);
		
		emCVC.addValueChangeListener(e -> enableEm(emCVC.getValue()));
		
		add.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	if(checkFields()) {
            	int idScore = Math.abs(id.hashCode()^Long.valueOf(System.currentTimeMillis()).hashCode());
        		LocalDate dateS = date.getValue();
        		int point = Integer.valueOf(score.getValue()).intValue();
        		boolean wa = wash.getValue().equalsIgnoreCase("sì")?true:false;
        		boolean ep = epa.getValue().equalsIgnoreCase("sì")?true:false;
        		boolean set = inf.getValue().equalsIgnoreCase("sì")?true:false;
        		String motMed = sostMed.getValue();
        		boolean md1 = med1.getValue().equalsIgnoreCase("Clorexidina alcolica")?true:false;
        		boolean md2 = med2.getValue().equalsIgnoreCase("iodio")?true:false;
        		Medication med = new Medication(md1, md2, glue.getValue(), biop.getValue());
        		String emo="";
        		if(emCVC.getValue().equalsIgnoreCase("altro")) {
        			emo = otherEm.getValue();
        		}
        		else
        		{
        			emo = emCVC.getValue();
        		}
        		String sig = sign.getValue();
        		//add field requested from medic
        		
        		ScoreForm scoreDB = new ScoreForm(idScore, Integer.valueOf(cvcId).intValue(), dateS, point, wa, ep, set, motMed, med, diffInf.getValue(), diffAsp.getValue(), sospInf.getValue(), obs.getValue(), emo, sig);
            	
            		if ( dao.ScoreCVCDao.addScoreCVC(scoreDB)) {
            				Notification notif = new Notification("SCORE SALVATO", Notification.Type.TRAY_NOTIFICATION);
            				notif.setDelayMsec(1000);//salvato con successo
            				notif.show(Page.getCurrent());
            				UI.getCurrent().getNavigator().navigateTo("");
            			}
            		else {
            			Notification notif = new Notification("SCORE NON SALVATO", Notification.Type.TRAY_NOTIFICATION);
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
		
	}
	
	private boolean checkFields() {
		if(!emCVC.isEmpty() && emCVC.getValue().equals("Altro")) {
			return otherEm.getValue().isEmpty();
		}
		return !(date.isEmpty()||score.isEmpty()||wash.isEmpty()||epa.isEmpty()||inf.isEmpty()||sostMed.isEmpty()||med1.isEmpty()||med2.isEmpty()||sign.getValue().isEmpty());
	}

	private void enableEm(String value) {
		if(value.equalsIgnoreCase("altro")) {
			otherEm.setEnabled(true);
		}
		else {
			otherEm.setEnabled(false);
			otherEm.clear();
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("valore cvc id "+event.getParameters());
		UI.getCurrent().setContent(new AddScoreView(event.getParameters()));
		
	}

}
