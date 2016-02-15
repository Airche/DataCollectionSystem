package com.legend.service;

import com.legend.model.Page;

public interface PageService {

	void saveOrUpdate(Page model);

	Page getPageById(int pageId);

}
