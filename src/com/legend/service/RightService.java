package com.legend.service;

import java.util.List;
import java.util.Set;

import com.legend.model.security.Right;

public interface RightService extends BaseService<Right> {

	void saveOrUpdateRight(Right model);

	void appendRightByURL(String url);

	void batchUpdateRights(List<Right> allRights);

	List<Right> findNotInRange(Set<Right> rights);

	int getMaxRightPos();

}
