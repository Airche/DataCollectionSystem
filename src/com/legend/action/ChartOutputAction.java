package com.legend.action;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.Page;
import com.legend.model.statistics.OptionStatisticsModel;
import com.legend.model.statistics.QuestionStatisticsModel;
import com.legend.service.StatisticsService;

@Controller("chartOutputAction")
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Page>{

	private Integer questionId;
	private int charType;
	
	@Resource(name="statisticsService")
	private StatisticsService statisticsService;
	
	public String execute(){
		return SUCCESS;
	}
	
	public JFreeChart getChart(){
		QuestionStatisticsModel qsm = this.statisticsService.statistics(questionId);
		DefaultPieDataset pds = new DefaultPieDataset();
		JFreeChart chart = null;
		for(OptionStatisticsModel osm : qsm.getOsms()){
			pds.setValue(osm.getOptionLabel(), osm.getCount());
		}
		try {
			switch(charType){
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
					chart = this.drawPieChart(pds,qsm);
					break;
				default:
					break;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return chart;
	}
	
	private JFreeChart drawPieChart(DefaultPieDataset pds,QuestionStatisticsModel qsm) throws IOException {

		// 创建JFreeChart对象（饼图）
//		JFreeChart chart = ChartFactory.createPieChart3D("标题", pds, true, false, false);
		 JFreeChart chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), pds, true, false, false);

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

/*		改用插件方式
 * 		ByteArrayOutputStream baos = new ByteArrayOutputStream();;
		ChartUtilities.writeChartAsJPEG(baos, chart, 800, 500);
		byte[] bArray = baos.toByteArray();
		baos.close();
		ByteArrayInputStream bais = new ByteArrayInputStream(bArray);*/
		return chart;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public int getCharType() {
		return charType;
	}

	public void setCharType(int charType) {
		this.charType = charType;
	}
	
	
}
