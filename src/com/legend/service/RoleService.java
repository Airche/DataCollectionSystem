package com.legend.service;

import com.legend.model.security.Role;

public interface RoleService extends BaseService<Role> {

	Role getEntityWithChild(int roleId);

	void saveOrUpdateRole(Role model, Integer[] ownRightsIds);

}
