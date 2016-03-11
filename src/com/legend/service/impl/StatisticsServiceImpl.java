package com.legend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Answer;
import com.legend.model.Question;
import com.legend.model.statistics.OptionStatisticsModel;
import com.legend.model.statistics.QuestionStatisticsModel;
import com.legend.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource(name = "questionDao")
	private BaseDao<Question> questionDao;

	@Resource(name = "answerDao")
	private BaseDao<Answer> answerDao;

	@Override
	public QuestionStatisticsModel statistics(Integer qid) {
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		Question question = questionDao.getEntity(Question.class, qid);
		qsm.setQuestion(question);

		String qhql = "from Answer where questionId = ? ";
		qsm.setCount(questionDao.findEntityByHql(qhql, qid).size());

		int qt = question.getQuestionType();
		switch (qt) {
			// 非矩阵式
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				String[] arr = question.getOptions().split("-");
				OptionStatisticsModel osm = null;
				for(int i=0;i<arr.length;++i){
					osm = new OptionStatisticsModel();
					osm.setOptionIndex(i);
					osm.setOptionLabel(arr[i]);
					String ohql = "from Answer where questionId = ? and concat(',',answerIds,',') like ?";
					osm.setCount(answerDao.findEntityByHql(ohql, qid,"%,"+arr[i]+",%").size());
					qsm.getOsms().add(osm);
				}
				if(question.getOther()!=null && question.getOther()){
					osm = new OptionStatisticsModel();
					osm.setOptionLabel("其它");
					String ohql = "from Answer where questionId = ? and concat(',',answerIds,',') like ? ";
					osm.setCount(answerDao.findEntityByHql(ohql, qid,"%,other,%").size());
					qsm.getOsms().add(osm);
				}
				break;
				// 矩阵式
			case 6:
			case 7:
			case 8:
				break;
		}
		return qsm;
	}

}
