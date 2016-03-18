package com.legend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.BaseEntity;
import com.legend.model.security.Right;
import com.legend.model.security.Role;
import com.legend.service.RoleService;
import com.legend.util.DataUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	@Resource(name="roleDao")
	public void setBaseDao(BaseDao<Role> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Role getEntityWithChild(int roleId) {
		Role role = this.getEntity(Role.class, roleId);
		role.getRights().size();
		return role;
	}

	@Override
	public void saveOrUpdateRole(Role model, Integer[] ownRightsIds) {
		Set<Right> rights = new HashSet<Right>();
		if(ownRightsIds!=null){
			for(Integer ownRightsId : ownRightsIds){
				Right right = new Right();
				right.setId(ownRightsId);
				rights.add(right);
			}
		}
		model.setRights(rights);
		this.saveOrUpdateEntity(model);
	}

	@Override
	public List<Role> findNotInRange(Set<Role> roles) {
		String hql = "from Role " + DataUtil.extractWhereStringIds(roles);
		return this.findEntityByHql(hql);
	}


	
}
