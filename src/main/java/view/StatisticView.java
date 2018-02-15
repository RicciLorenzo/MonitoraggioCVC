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
	private Component chartPatient;
	private Component chartInsM;
	private Component chartDiffI;
	private Component chartFis;
	private Component chartRx;
	private Component chartComp;
	private Component chartComps;
	private Component chartPosE;
	private Component chartTip;
	private Component chartWay;
	private Component chartStruct;

	
	
	public StatisticView() {
			back.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(""));
			chartInsM = getChartInsM();
			chartDiffI = getChartDiffI();
			chartFis = getChartFis();
			chartRx = getChartRx();
			chartComp = getChartComp();
			chartComps = getChartComps();
			chartPosE = getChartPosE();
			chartTip = getChartTip();
			chartWay = getChartWay();
			
			this.addComponents(back, date, chartInsM, chartDiffI, chartPosE, chartRx, chartComp, chartComps, chartFis, chartTip, chartWay);
			this.setComponentAlignment(date, Alignment.TOP_CENTER);
			this.setComponentAlignment(back, Alignment.TOP_RIGHT);
			this.setComponentAlignment(chartInsM, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartDiffI, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartPosE, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartRx, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartComp, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartComps, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartFis, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartTip, Alignment.MIDDLE_CENTER);
			this.setComponentAlignment(chartWay, Alignment.MIDDLE_CENTER);

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
	
	private Component getChartDiffI() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Difficoltà Inserimento");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getDiffI(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Sì", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("No", programmedValues);
		
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
	
	
	
	private Component getChartPosE() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Posizionamento Ecoguidato");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getPosE(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Sì", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("No", programmedValues);
		
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
	
	
	private Component getChartRx() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica RX Torace");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getRx(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Sì", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("No", programmedValues);
		
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
	
	private Component getChartComp() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Complicanze");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getComp(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Sì", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("No", programmedValues);
		
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
	
	private Component getChartTip() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Punta");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getTip(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));

		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Aperta", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("Chiusa", programmedValues);
		
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
	
	
	private Component getChartWay() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica N Way");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();
		List<HighChartsData> thirdValues = new ArrayList<>();
		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getWay(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));
			thirdValues.add(new IntData(values[2]));
		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("1", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("2", programmedValues);
		ColumnChartSeries thirdColumn = new ColumnChartSeries("3", thirdValues);
		
		column.getSeriesList().add(urgentColumn);
		column.getSeriesList().add(programmedColumn);
		column.getSeriesList().add(thirdColumn);
		
		try {
			chart = HighChartFactory.renderChart(column);
		}
		catch (HighChartsException e){
			e.printStackTrace();
		}
		
		return chart;
	}
	
	private Component getChartFis() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Fissaggio");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();
		List<HighChartsData> thirdValues = new ArrayList<>();
		List<HighChartsData> fourthValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getFis(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));
			thirdValues.add(new IntData(values[2]));
			fourthValues.add(new IntData(values[3]));
		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Griplock", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("Statlock", programmedValues);
		ColumnChartSeries thirdColumn = new ColumnChartSeries("Securacath", thirdValues);
		ColumnChartSeries fourthColumn = new ColumnChartSeries("Altro", fourthValues);

		
		column.getSeriesList().add(urgentColumn);
		column.getSeriesList().add(programmedColumn);
		column.getSeriesList().add(thirdColumn);
		column.getSeriesList().add(fourthColumn);
		
		try {
			chart = HighChartFactory.renderChart(column);
		}
		catch (HighChartsException e){
			e.printStackTrace();
		}
		
		return chart;
	}
	
	private Component getChartComps() {
		
		HighChart chart = null;	
		
		ChartConfiguration column = new ChartConfiguration();
		column.setTitle("Statistica Complicanze");
		column.setChartType(ChartType.COLUMN);
		
		Axis x = buildAxis();
		column.setxAxis(x);
		
		ColumnChartPlotOptions columnOptions = new ColumnChartPlotOptions();
		column.setPlotOptions(columnOptions);
		
		List<HighChartsData> urgentValues = new ArrayList<>();
		List<HighChartsData> programmedValues = new ArrayList<>();
		List<HighChartsData> thirdValues = new ArrayList<>();
		List<HighChartsData> fourthValues = new ArrayList<>();

		
		for(LocalDate d : dates) {
			int[] values = dao.StatisticDao.getComps(d);
			
			urgentValues.add(new IntData(values[0]));
			programmedValues.add(new IntData(values[1]));
			thirdValues.add(new IntData(values[2]));
			fourthValues.add(new IntData(values[3]));
		}
		
		ColumnChartSeries urgentColumn = new ColumnChartSeries("Ematoma", urgentValues);
		ColumnChartSeries programmedColumn = new ColumnChartSeries("Puntura Arteria", programmedValues);
		ColumnChartSeries thirdColumn = new ColumnChartSeries("PNX", thirdValues);
		ColumnChartSeries fourthColumn = new ColumnChartSeries("Altro", fourthValues);

		
		column.getSeriesList().add(urgentColumn);
		column.getSeriesList().add(programmedColumn);
		column.getSeriesList().add(thirdColumn);
		column.getSeriesList().add(fourthColumn);
		
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
