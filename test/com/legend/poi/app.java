package com.legend.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * excel -- sheet -- row -- cell 
 * HSSFWorkbook -- HSSFSheet -- HSSFRow -- HSSFCell
 */

public class app {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("first sheet");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(true);
		row.createCell(1).setCellValue(Calendar.getInstance());
		row.createCell(2).setCellValue(new Date());
		row.createCell(3).setCellValue(122333423.32331D);
		row.createCell(4).setCellValue(new HSSFRichTextString("dddddddddddddddddddddddddddddddddddddddd"));
		
		//创建数据格式对象
		HSSFDataFormat  format = wb.createDataFormat();
		
		//格式化数据
		HSSFCellStyle style = wb.createCellStyle();
		
		//设置样式的数据格式
		style.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
		
		//应用样式给单元格
		row.getCell(1).setCellStyle(style);
		row.getCell(2).setCellStyle(style);
		
		//格式化Double类型
		style = wb.createCellStyle();
		style.setDataFormat(format.getFormat("#,###.000"));
		row.getCell(3).setCellStyle(style);
		
		sheet.setColumnWidth(1, 5000);		//设置列宽
		sheet.autoSizeColumn(2);     				//自动列宽
		sheet.autoSizeColumn(3);     				//自动列宽
		sheet.setColumnWidth(4, 7000);		//设置列宽
		
		//自动换行
		style = wb.createCellStyle();
		style.setWrapText(true);
		row.getCell(4).setCellStyle(style);
		
		
		//设置文本对齐方式
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("左上");
		row.createCell(1).setCellValue("中中");
		row.createCell(2).setCellValue("右下");
		
		//设置行高
		row.setHeightInPoints(50);
		
		//设置文本对齐方式
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			//左对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);		//上对齐
		row.getCell(0).setCellStyle(style);
		
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		row.getCell(1).setCellStyle(style);
		
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		row.getCell(2).setCellStyle(style);
		
		//设置字体颜色和大小
		style = row.getCell(1).getCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontName("方正姚体");
		font.setFontHeightInPoints((short) 30);
		font.setItalic(true);
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		
		//字体旋转
		style = row.getCell(1).getCellStyle();
		style.setRotation((short) -30);		//顺时针旋转30度
		
		//设置边框
		row = sheet.createRow(2);
		row.createCell(0).setCellValue("边框");
		style = wb.createCellStyle();
		style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
		style.setTopBorderColor(HSSFColor.BLUE.index);
		row.getCell(0).setCellStyle(style);
		
		//计算列
		row = sheet.createRow(3);
		row.createCell(0).setCellValue(13D);
		row.createCell(1).setCellValue(14D);
		row.createCell(2).setCellValue(19D);
		row.createCell(3).setCellFormula("sum(A4:C4)");
		
		//移动行
		//sheet.shiftRows(1, 3, 2);
		
		//拆分窗格
		//1000：左侧窗格的宽度；
		//2000：上侧窗格的高度；
		//3：右侧窗格开始显示列的索引；
		//4：下侧窗格开始显示行的索引；
		//0：激活的面板区
//		sheet.createSplitPane(1000, 2000, 3, 4, 0);
		
		//冻结窗口
		//1：左侧冻结的列
		//2：上侧冻结的行数
		//3：右侧窗格开始显示列的索引；
		//4：下侧窗格开始显示行的索引；
//		sheet.createFreezePane(1, 2, 3, 4);
		
		//合并单元格
		CellRangeAddress d = new CellRangeAddress(1, 3, 1, 3);
		sheet.addMergedRegion(d);
		
		wb.write(new FileOutputStream("f:/poi.xls"));
	}

}
