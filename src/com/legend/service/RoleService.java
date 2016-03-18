package com.legend.service;

import java.util.List;
import java.util.Set;

import com.legend.model.security.Role;

public interface RoleService extends BaseService<Role> {

	Role getEntityWithChild(int roleId);

	void saveOrUpdateRole(Role model, Integer[] ownRightsIds);

	List<Role> findNotInRange(Set<Role> roles);

}
