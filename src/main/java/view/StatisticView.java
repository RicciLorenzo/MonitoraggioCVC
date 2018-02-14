package view;

import java.time.LocalDate;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")

public class StatisticView extends VerticalLayout implements View {
	
	public final static String NAME = "STATISTIC_VIEW";
	private LocalDate date = LocalDate.now();
	private static LocalDate[] dates = buildLastDay();
	
	
	public StatisticView() {
			boolean first = true; //to pass current day instead of last day of month
			switch(date.getMonthValue()) {
				case 12:
					if(first) {
						first=false;
					}
					else {
						
					}
					
				case 11:	
					if(first) {
						first=false;
					}
					else {
						
					}
				case 10:	
					if(first) {
						first=false;
					}
					else {
						
					}
				case 9:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 8:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 7:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 6:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 5:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 4:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 3:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 2:
					if(first) {
						first=false;
					}
					else {
						
					}
				case 1:
					
			}
	}
	
	private static LocalDate[] buildLastDay() {
		LocalDate[] ret = {null,null,null,null,null,null,null,null,null,null,null,null};
		
		return ret;
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new StatisticView());
	}

}
