package com.legend.jfreechart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 
 * JFreeChart测试
 */
public class app {

	public static void main(String[] args) throws IOException {

		// drawPieChart();			//饼图
		//	drawBarChart();			//柱状图
		drawLineChart();			//折线图

	}
	
	private static void drawLineChart() throws IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(3400, "IBM", "一季度");
		ds.addValue(3100, "ORACLE", "一季度");
		ds.addValue(1950, "用友", "一季度");
		
		ds.addValue(3800, "IBM", "二季度");
		ds.addValue(3900, "ORACLE", "二季度");
		ds.addValue(1150, "用友", "二季度");
		
		ds.addValue(3000, "IBM", "三季度");
		ds.addValue(3500, "ORACLE", "三季度");
		ds.addValue(1350, "用友", "三季度");
		JFreeChart chart = ChartFactory.createLineChart("标题", "X标题", "Y标题", ds, PlotOrientation.VERTICAL, true, false, false);
//		JFreeChart chart = ChartFactory.createLineChart3D("标题", "X标题", "Y标题", ds, PlotOrientation.VERTICAL, true, false, false);
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));							//设置标题字体
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(new Font("宋体",Font.ITALIC,15));		//设置X轴字体
		plot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.ITALIC,15));		//设置X轴小标签字体
		plot.getRangeAxis().setLabelFont(new Font("宋体",Font.ITALIC,15));			//设置Y轴字体
		chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,10));				//设置提示条字体
		ChartUtilities.saveChartAsJPEG(new File("f://line.jpg"), chart, 800, 500);
	}

	private static void drawBarChart() throws IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(3400, "IBM", "一季度");
		ds.addValue(3100, "ORACLE", "一季度");
		ds.addValue(1950, "用友", "一季度");
		
		ds.addValue(3800, "IBM", "二季度");
		ds.addValue(3900, "ORACLE", "二季度");
		ds.addValue(1150, "用友", "二季度");
		
		ds.addValue(3000, "IBM", "三季度");
		ds.addValue(3500, "ORACLE", "三季度");
		ds.addValue(1350, "用友", "三季度");
//		JFreeChart chart = ChartFactory.createBarChart("标题", "X标题", "Y标题", ds, PlotOrientation.VERTICAL, true, false, false);
		JFreeChart chart = ChartFactory.createBarChart3D("标题", "X标题", "Y标题", ds, PlotOrientation.VERTICAL, true, false, false);
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));							//设置标题字体
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(new Font("宋体",Font.ITALIC,15));		//设置X轴字体
		plot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.ITALIC,15));		//设置X轴小标签字体
		plot.getRangeAxis().setLabelFont(new Font("宋体",Font.ITALIC,15));			//设置Y轴字体
		chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,10));				//设置提示条字体
		ChartUtilities.saveChartAsJPEG(new File("f://bar.jpg"), chart, 800, 500);
	}

	private static void drawPieChart() throws IOException {
		DefaultPieDataset pds = new DefaultPieDataset();
		pds.setValue("IBM", 4000);
		pds.setValue("ORACLE", 6000);
		pds.setValue("HP", 600);
		pds.setValue("用友", 20000);

		// 创建JFreeChart对象（饼图）
		JFreeChart chart = ChartFactory.createPieChart3D("标题", pds, true, false, false);
		// JFreeChart chart = ChartFactory.createPieChart("标题", pds, true,
		// false, false);

		// 设置标题字体
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));

		// 设置图例字体
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 10));

		// 设置绘图字体
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.ITALIC, 10));
		// 设置绘图背景图片
		plot.setBackgroundImage(ImageIO.read(new File("f://欢迎扫描.jpg")));

		// 设置图表背景图片
		chart.setBackgroundImage(ImageIO.read(new File("f://111111.png")));

		// 设置分离效果,3D不支持
		plot.setExplodePercent("IBM", 0.1);

		//设置3D前景透明
		plot.setForegroundAlpha(0.75f);

		// 定制标签
		// {0}:公司名称 {1}:公司销售额 {2}:百分比 {3}:总和 {4}:没有了
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));

		ChartUtilities.saveChartAsJPEG(new File("f://pie.jpg"), chart, 800, 500);
	}
}
