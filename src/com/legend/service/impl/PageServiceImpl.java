package com.legend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Page;
import com.legend.service.PageService;

@Service("pageService")
public class PageServiceImpl implements PageService {

	@Resource(name="pageDao")
	private BaseDao pageDao;
	
	@Override
	public void saveOrUpdate(Page model) {
		this.pageDao.saveOrUpdateEntity(model);
	}

	@Override
	public Page getPageById(int pageId) {
		return (Page) this.pageDao.getEntity(Page.class, pageId);
	}

}
