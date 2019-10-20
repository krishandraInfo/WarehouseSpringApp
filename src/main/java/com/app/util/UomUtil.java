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
public class UomUtil {
	public void generatePieChart(String path,List<Object[]> uomTypes) {

		//1.Create data set
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for(Object[] d:uomTypes) {
			dataSet.setValue(d[0].toString(), new Double(d[1].toString()));
		}

		//2.converting data to JFreeChart
		JFreeChart jFreeChart=ChartFactory.createPieChart3D("UOM Pie Chart", dataSet, true, true, false);

		//3.save image as JPEG
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/UomTypepie.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void generateBarChart(String path,List<Object[]> uomTypes) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] d: uomTypes) {
			dataset.setValue(new Double(d[1].toString()), d[0].toString(), "");
		}

		JFreeChart jFreeChart=ChartFactory.createBarChart("UOM Bar Chart", "Order Mode", "Count", dataset);

		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/resources/images/UomTypebar.jpg"), jFreeChart, 250, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
