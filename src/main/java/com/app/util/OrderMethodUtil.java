package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class OrderMethodUtil {
	
	public void generatePieChart(String path,List<Object[]> orderModes) {
		
		//1.Create data set
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for(Object[] d:orderModes) {
			dataSet.setValue(d[0].toString(), new Double(d[1].toString()));
		}
		
		//2.converting data to JFreeChart
		JFreeChart jFreeChart=ChartFactory.createPieChart3D("Order Mode Pie Chart", dataSet, true, true, false);
		
		//3.save image as JPEG
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/Ordermethodpie.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void generateBarChart(String path,List<Object[]> orderModes) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] d: orderModes) {
			dataset.setValue(new Double(d[1].toString()), d[0].toString(), "");
		}
		
		JFreeChart jFreeChart=ChartFactory.createBarChart("Order Mode Bar Chart", "Order Mode", "Count", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/Ordermethodbar.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
