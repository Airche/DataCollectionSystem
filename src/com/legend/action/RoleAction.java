package com.legend.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.security.Right;
import com.legend.model.security.Role;
import com.legend.service.RightService;
import com.legend.service.RoleService;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
		private int roleId;
		private List<Role> allRoles;
		private List<Right> noOwnRights;
		private Integer[] ownRight;

		@Resource(name="roleService")
		private RoleService roleService;
		
		@Resource(name="rightService")
		private RightService rightService;
		
		public String findAllRoles(){
			this.allRoles = this.roleService.findAllEntities();
			return "roleListPage";
		}
		
		public String toAddRolePage(){
			this.noOwnRights = this.rightService.findAllEntities();
			return "addRolePage";
		}
		
		public String toEditRolePage(){
			this.model = this.roleService.getEntityWithChild(this.roleId);
			this.noOwnRights = this.rightService.findNotInRange(this.model.getRights());
			return "editRolePage";
		}
		
		public String deleteRole(){
			Role role = new Role();
			role.setId(roleId);
			this.roleService.deleteEntity(role);
			return "roleListPageAction";
		}
		
		public String saveOrUpdateRole(){
			this.roleService.saveOrUpdateRole(this.model,this.ownRight);
			return "roleListPageAction";
		}

		public List<Role> getAllRoles() {
			return allRoles;
		}

		public void setAllRoles(List<Role> allRoles) {
			this.allRoles = allRoles;
		}

		public List<Right> getNoOwnRights() {
			return noOwnRights;
		}

		public void setNoOwnRights(List<Right> noOwnRights) {
			this.noOwnRights = noOwnRights;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public Integer[] getOwnRight() {
			return ownRight;
		}

		public void setOwnRight(Integer[] ownRight) {
			this.ownRight = ownRight;
		}
		
}
