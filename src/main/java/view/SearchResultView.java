package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import model.CVCPreview;

@SuppressWarnings("serial")

public class SearchResultView extends VerticalLayout implements View{

	public final static String NAME = "SEARCH_RESULT";
	
	private Label nores = new Label("Non ci sono risultati corrispondenti alla ricerca");
	
	private Button back = new Button("Indietro");
	
	
	
	public SearchResultView(String name) {
		
		System.out.println(name);
		setMargin(true);
		setSpacing(true);
		addComponents(back, nores);
		nores.setVisible(false);
		setComponentAlignment(back, Alignment.TOP_RIGHT);
		back.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(""));
		if(name.equals("") || !(dao.PatientDao.patientExist(name)))
			nores.setVisible(true);
		for(CVCPreview cvc: dao.CVCDao.CVCPreview(name)) {
			addComponent(result(cvc));
		}
		
	}
	
	
	//use model.cvcpreview 
	private Component result(CVCPreview cvcP) {
		HorizontalLayout hor = new HorizontalLayout();
		GridLayout res = new GridLayout(6,2);
		res.setSizeUndefined();
		Panel pan = new Panel();
		Panel pan1 = new Panel();
		pan.setSizeUndefined();
		
		Label name = new Label("Nome");
		Label surname = new Label("Cognome");
		Label date = new Label("Data di Nascita");
		Label fiscal = new Label("Codice Fiscale");
		Label ins = new Label("Inserimento");
		Label closed = new Label("Chiuso");
		Button vis = new Button("Visualizza");
		Button cvc = new Button("Aggiungi CVC al Paziente");
		
		Label nameT = new Label(cvcP.getName());
		Label surnameT = new Label(cvcP.getSurname());
		Label birthT = new Label(cvcP.getBirthday());
		Label fiscalT = new Label(cvcP.getFiscalCode());
		Label insT = new Label(cvcP.getSite());
		Label closedT = new Label(cvcP.getClosed()?"Sì":"No");
		
		res.addComponents(name, surname, date, fiscal, ins, closed, nameT, surnameT, birthT, fiscalT, insT, closedT);
		
		res.setMargin(true);
		res.setSpacing(true);
		pan1.setContent(res);
		hor.addComponents(pan1, vis, cvc);
		vis.addClickListener(e -> goToCVC(cvcP.getCVCId()));
		hor.setMargin(true);
		hor.setSpacing(true);
		hor.setComponentAlignment(vis, Alignment.MIDDLE_CENTER);
		hor.setComponentAlignment(cvc, Alignment.MIDDLE_CENTER);
		pan.setContent(hor);
		return pan;
	}
	

	
	private void goToCVC(int id) {
		UI.getCurrent().getNavigator().navigateTo(CVCView.NAME+"/"+id);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().getNavigator().setErrorView(new SearchResultView(""));
		System.out.println("ricerca eff???" + event.getParameters());
		UI.getCurrent().setContent(new SearchResultView(event.getParameters()));
	}

	
}
