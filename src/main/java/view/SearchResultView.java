package view;

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

public class SearchResultView extends VerticalLayout implements View{

	public final static String NAME = "SEARCH_RESULT";
	private Label nores = new Label("Non ci sono risultati corrispondenti alla ricerca");
	
	private Button back = new Button("Indietro");
	
	
	
	public SearchResultView(String name) {
		setMargin(true);
		setSpacing(true);
		addComponents(back, nores);
		setComponentAlignment(back, Alignment.TOP_RIGHT);
		back.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(""));
		//if(name.equals(null))
			nores.setVisible(true);
		addComponent(result());
		
	}
	
	
	
	private Component result() {
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
		
		Label nameT = new Label("Mario");
		Label surnameT = new Label("Rossi");
		Label birthT = new Label("17/09/1994");
		Label fiscalT = new Label("RSSMRI94P17M172G");
		Label insT = new Label("Succlavia DX");
		Label closedT = new Label("No");
		
		res.addComponents(name, surname, date, fiscal, ins, closed, nameT, surnameT, birthT, fiscalT, insT, closedT);
		
		res.setMargin(true);
		res.setSpacing(true);
		pan1.setContent(res);
		hor.addComponents(pan1, vis);
		
		hor.setMargin(true);
		hor.setSpacing(true);
		hor.setComponentAlignment(vis, Alignment.MIDDLE_CENTER);
		pan.setContent(hor);
		return pan;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("ricerca eff???" + event.getParameters());
		UI.getCurrent().setContent(new SearchResultView(event.getParameters()));
		Page.getCurrent().reload();
	}

	
}
