package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.Axis;
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
	private Button back = new Button("Indietro");
	private Label date = new Label(LocalDate.now().toString());
	
	public StatisticView() {
			back.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(""));
			Component chartInsM = getChartInsM();
			this.addComponents(back, date, chartInsM);
			this.setComponentAlignment(date, Alignment.TOP_CENTER);
			this.setComponentAlignment(back, Alignment.TOP_RIGHT);
			this.setComponentAlignment(chartInsM, Alignment.MIDDLE_CENTER);
			
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

	private Axis buildAxis() {
		Axis x = new Axis(Axis.AxisType.xAxis);
		x.getCategories().add("Gen");
		x.getCategories().add("Feb");
		x.getCategories().add("Mar");
		x.getCategories().add("Apr");
		x.getCategories().add("Mag");
		x.getCategories().add("Giu");
		x.getCategories().add("Lug");
		x.getCategories().add("Ago");
		x.getCategories().add("Set");
		x.getCategories().add("Ott");
		x.getCategories().add("Nov");
		x.getCategories().add("Dic");
	
		return x;
	}
	
	private Component getChartInsM() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Modalità Inserimento");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getInsM(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Urgente", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("Programmato", programmedValues);
		
		column.getSeriesList().add(urgentColumn);
		column.getSeriesList().add(programmedColumn);
		
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
