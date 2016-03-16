package com.legend.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.security.Right;
import com.legend.service.RightService;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	@Override
	@Resource(name = "rightDao")
	public void setBaseDao(BaseDao<Right> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public void saveOrUpdateRight(Right model) {
		int pos = 0;
		long code = 1L;
		if (model.getId() == null) {
			// String hql = "from Right order by rightPos Desc , rightCode
			// desc";
			// List<Right> rights = this.findEntityByHql(hql);
			// if(rights==null||rights.isEmpty()){
			// pos = 0;
			// code = 1L;
			// }else{
			// //得到最上面的权限堆栈
			// Right top = rights.get(0);
			// int topPos = top.getRightPos();
			// long topCode = top.getRightCode();
			// if(topCode >= (1L<<60)){
			// pos = topPos + 1;
			// code = 1L;
			// }else{
			// pos = topPos;
			// code = topCode << 1;
			// }
			// }
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r where r.rightPos = (select max(rr.rightPos) from Right rr)";
			Object[] arr = this.uniqueResult(hql);
			if (arr[0] == null || arr.length == 0) {
				pos = 0;
				code = 1L;
			} else {
				int topPos = (int) arr[0];
				long topCode = (long) arr[1];
				if (topCode >= (1L << 60)) {
					pos = topPos + 1;
					code = 1L;
				} else {
					pos = topPos;
					code = topCode << 1;
				}
			}
			model.setRightPos(pos);
			model.setRightCode(code);
		}

		this.saveOrUpdateEntity(model);
	}

	@Override
	public void appendRightByURL(String url) {
		String hql = "from Right where rightUrl=?";
		List<Right> rights = this.findEntityByHql(hql, url);
		if (rights.size() == 0) {
			Right right = new Right();
			right.setRightUrl(url);
			this.saveOrUpdateRight(right);
		}
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		for (Right r : allRights) {
			this.saveOrUpdateRight(r);
		}
	}

	@Override
	public List<Right> findNotInRange(Set<Right> rights) {
		String hql = "from Right where ";
		String where = null;
		if(rights==null||rights.size()==0){
			where = "1=1";
		}else{
			String temp = "" ;
			for(Right r: rights){
				temp += r.getId()+",";
			}
			temp = temp.substring(0, temp.lastIndexOf(','));
			where = "id not in ( "+ temp +")";
		}
		hql += where ;
		return this.findEntityByHql(hql);
	}
}
