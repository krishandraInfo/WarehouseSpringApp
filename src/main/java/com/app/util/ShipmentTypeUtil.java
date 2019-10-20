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
public class ShipmentTypeUtil {

	public void generatePieChart(String path,List<Object[]> shipmentTypes) {
		
		//1.creating dataset based on shipment type count
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] d : shipmentTypes) {
			dataset.setValue(d[0].toString(), new Double(d[1].toString()));
		}
		
		//2.convert data to J FREE CHART using chart factory
		JFreeChart jFreeChart=ChartFactory.createPieChart3D("Shipment Types Pie Chart", dataset, true, true, false);
		
		//3.save chart as jpeg image
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/shipmentpie.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateBarChart(String path,List<Object[]> shipmentTypes) {
		
		//1.create dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] d:shipmentTypes) {
			dataset.setValue(new Double(d[1].toString()), d[0].toString(), "");
		}
		//2.convert data to JFree Chart
		JFreeChart jFreeChart=ChartFactory.createBarChart("Shipment Types Data", "Shipment Type", "Count", dataset);
		
		//3.save as image
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/shipmentbar.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
