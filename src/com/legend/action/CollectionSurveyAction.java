package com.legend.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.Answer;
import com.legend.model.Question;
import com.legend.model.Survey;
import com.legend.service.SurveyService;

@Controller("collectionSurveyAction")
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey> {
	private int surveyId;
	private String excelFileName;

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	public String execute() {
		return SUCCESS;
	}

	public InputStream getIs() {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayInputStream bais = null;
		Map<Integer,Integer>  qidIndexMap = new HashMap<Integer,Integer>();
		try {
			List<Question> questions = this.surveyService.getQuestions(surveyId);
			this.excelFileName = String.valueOf(surveyId) + ".xls";
			HSSFSheet sheet = wb.createSheet();
			HSSFRow row = sheet.createRow(0);
			HSSFCellStyle style = wb.createCellStyle();
			for (int i = 0; i < questions.size(); ++i) {
				Question q = questions.get(i);
				row.createCell(i).setCellValue(q.getTitle());
				sheet.autoSizeColumn(i);
				qidIndexMap.put(q.getId(), i);
			}
			List<Answer> answers = surveyService.getAnswers(surveyId);
			String oldUuid=null;
			String newUuid=null;
			int rowIndex = 0;
			for(Answer answer: answers ){
				newUuid = answer.getUuid();
				if(!newUuid.equals(oldUuid)){
					++rowIndex;
					oldUuid = newUuid;
					row = sheet.createRow(rowIndex);
				}
				row.createCell(qidIndexMap.get(answer.getQuestionId())).setCellValue(answer.getAnswerIds());
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			bais = new ByteArrayInputStream(baos.toByteArray());
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bais;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

}
