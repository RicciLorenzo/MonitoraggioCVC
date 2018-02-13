package view;

import java.time.LocalDate;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")

public class StatisticView extends VerticalLayout implements View {
	
	public final static String NAME = "STATISTIC_VIEW";
	private LocalDate date;
	
	public StatisticView(LocalDate date) {
		
	}
	
	
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new StatisticView(LocalDate.parse(event.getParameters().toString())));
	}

}
