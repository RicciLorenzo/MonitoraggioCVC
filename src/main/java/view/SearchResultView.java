package view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
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
import model.Patient;

@SuppressWarnings("serial")


public class SearchResultView extends VerticalLayout implements View{

	public final static String NAME = "SEARCH_RESULT";
	
	private Label nores = new Label("Non ci sono risultati corrispondenti alla ricerca");
	
	private Button back = new Button("Indietro");
	
	
	
	public SearchResultView(String name) {
		Page.getCurrent().setTitle("Risultati Ricerca");
		if(name.matches("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$")) {
			System.out.println("Codice fiscale");
		}
		System.out.println(name);
		setMargin(true);
		setSpacing(true);
		addComponents(back, nores);
		nores.setVisible(false);
		setComponentAlignment(back, Alignment.TOP_RIGHT);
		back.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(""));
		boolean first = true;
		if(name.equals("") || (!(dao.PatientDao.patientExist(name)) && !(dao.PatientDao.patientExistNS(name)) && !(dao.PatientDao.patientExistNAS(name)))) {
			nores.setVisible(true);
		}
		if(name.matches("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$")) {
			System.out.println("path codice fiscale normale");
			if(first) {
				this.addComponent(buildPatient(dao.PatientDao.getPatient(name)));
				first=false;
			}
			for(CVCPreview cvc: dao.CVCDao.CVCPreview(name)) {
				System.out.println("ci entro qua cod fisclae");
				
				addComponent(result(cvc));
			}
		} 
		else if (!dao.PatientDao.patientExistNAS(name)){
			System.out.println("name per ricerca nome:"+name);
			for (String fiscalCode: dao.PatientDao.getFiscalFromSName(name)) {
				System.out.println("path codice nome/cognome"+fiscalCode);
				this.addComponent(buildPatient(dao.PatientDao.getPatient(fiscalCode)));
				
			}
			for (String fiscalCode: dao.PatientDao.getFiscalFromSName(name)) {
				for(CVCPreview cvc: dao.CVCDao.CVCPreview(fiscalCode)) {
					System.out.println("path codice fiscale dopo nome/cognome");
					
						
					
					addComponent(result(cvc));
					}
				
			}
			
		}
		else {
			//sql query for name and surname of patient returns arraylist<String> fiscal code
			//SELECT * FROM patient WHERE CONCAT(name,' ',surname) ILIKE 'mario rossi' OR CONCAT(surname,' ',name) ILIKE 'rossi mario'
			for (String fiscalCode: dao.PatientDao.patientCodeNAS(name)) {
				System.out.println("path codice nome+cognome"+fiscalCode);
				this.addComponent(buildPatient(dao.PatientDao.getPatient(fiscalCode)));
				for(CVCPreview cvc: dao.CVCDao.CVCPreview(fiscalCode)) {
					System.out.println("path codice fiscale dopo nome+cognome");
					
						
					
					addComponent(result(cvc));
					}
				
			}
		
		}
		
	}

	
	private Component buildPatient(Patient p) {
		HorizontalLayout hor = new HorizontalLayout();
		GridLayout res = new GridLayout(4,2);
		res.setSizeUndefined();
		Panel pan = new Panel();
		Panel pan1 = new Panel();
		pan.setSizeUndefined();
		
		Label name = new Label("Nome");
		Label surname = new Label("Cognome");
		Label date = new Label("Data di Nascita");
		Label fiscal = new Label("Codice Fiscale");
		Button vis = new Button("Modifica Posizionamento");
		Button cvc = new Button("Aggiungi CVC al Paziente");
		
		Label nameT = new Label(p.getName());
		Label surnameT = new Label(p.getSurname());
		Label birthT = new Label(p.getBirthday());
		Label fiscalT = new Label(p.getFiscalCode());

		
		res.addComponents(name, surname, date, fiscal, nameT, surnameT, birthT, fiscalT);
		
		res.setMargin(true);
		res.setSpacing(true);
		pan1.setContent(res);
		hor.addComponents(pan1, vis, cvc);
		vis.addClickListener(e -> goToPatient(p));
		cvc.addClickListener(e -> goToAddCVC(p.getFiscalCode()));
		hor.setMargin(true);
		hor.setSpacing(true);
		hor.setComponentAlignment(vis, Alignment.MIDDLE_CENTER);
		hor.setComponentAlignment(cvc, Alignment.MIDDLE_CENTER);
		pan.setContent(hor);
		return pan;
	}
	
	
	private void goToAddCVC(String fiscalCode) {
		UI.getCurrent().getNavigator().addView(AddCVCView.NAME, new AddCVCView(fiscalCode));
		UI.getCurrent().getNavigator().navigateTo(AddCVCView.NAME+"/"+fiscalCode);
	}

	private void goToPatient(Patient p) {
		System.out.println("codeice fiscael"+p.getFiscalCode());
		UI.getCurrent().getNavigator().addView(PatientView.NAME, new PatientView(p.getFiscalCode()));
		UI.getCurrent().getNavigator().navigateTo(PatientView.NAME+"/"+p.getFiscalCode());
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
		cvc.addClickListener(e -> goToAddCVC(cvcP.getFiscalCode()));
		hor.setMargin(true);
		hor.setSpacing(true);
		hor.setComponentAlignment(vis, Alignment.MIDDLE_CENTER);
		hor.setComponentAlignment(cvc, Alignment.MIDDLE_CENTER);
		pan.setContent(hor);
		return pan;
	}
	

	
	private void goToCVC(int id) {
		System.out.println("spostamento in scheda cvc");
        UI.getCurrent().getNavigator().addView(CVCView.NAME, new CVCView(id));
		UI.getCurrent().getNavigator().navigateTo(CVCView.NAME+"/"+String.valueOf(id));
	}


	@Override
	public void enter(ViewChangeEvent event) {
		String code = event.getParameters().trim().replace(' ', '_').replace("'", "''");
		UI.getCurrent().getNavigator().setErrorView(new SearchResultView(""));
		System.out.println("ricerca eff???" + event.getParameters());
		UI.getCurrent().setContent(new SearchResultView(code));
	}

	
}
