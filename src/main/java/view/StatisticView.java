package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.data.HighChartsData;
import at.downdrown.vaadinaddons.highchartsapi.model.data.base.IntData;
import at.downdrown.vaadinaddons.highchartsapi.model.plotoptions.ColumnChartPlotOptions;
import at.downdrown.vaadinaddons.highchartsapi.model.series.ColumnChartSeries;

@SuppressWarnings("serial")

public class StatisticView extends VerticalLayout implements View {
	
	public final static String NAME = "STATISTIC_VIEW";
	private static LocalDate[] dates = buildLastDay();
	
	public StatisticView() {
			this.addComponents(getChartInsM());
			
	}
	
	private static LocalDate[] buildLastDay() {
		LocalDate[] ret = {null,null,null,null,null,null,null,null,null,null,null,null};
		ret[0]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[1]= LocalDate.of(LocalDate.now().getYear(),1,(LocalDate.now().isLeapYear()?29:28));
		ret[2]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[3]= LocalDate.of(LocalDate.now().getYear(),1,30);
		ret[4]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[5]= LocalDate.of(LocalDate.now().getYear(),1,30);
		ret[6]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[7]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[8]= LocalDate.of(LocalDate.now().getYear(),1,30);
		ret[9]= LocalDate.of(LocalDate.now().getYear(),1,31);
		ret[10]= LocalDate.of(LocalDate.now().getYear(),1,30);
		ret[11]= LocalDate.of(LocalDate.now().getYear(),1,31);
		return ret;
	}

	private Component getChartInsM() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Modalità Inserimento");
		column.setChartType(ChartType.COLUMN);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		urgentValues.add(new IntData(7));
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Urgente", urgentValues);
		
		column.getSeriesList().add(urgentColumn);
		
		try {
			chart = HighChartFactory.renderChart(column);
		}
		catch (HighChartsException e){
			e.printStackTrace();
		}
		
		return chart;
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setContent(new StatisticView());
	}

}
