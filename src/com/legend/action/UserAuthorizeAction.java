package com.legend.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.User;
import com.legend.model.security.Role;
import com.legend.service.RoleService;
import com.legend.service.UserService;

@Controller("userAuthorizeAction")
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {
	
	private Integer userId;
	private List<User> allUsers;
	private List<Role> noOwnRolesList;
	private Integer[] ownRoles;
	private Integer[] noOwnRolesIds;

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	public String findAllUsers() {
		this.allUsers = this.userService.findAllEntities();
		return "userAuthorizeListPage";
	}
	
	public String toEditAuthorize(){
		this.model = this.userService.getEntityWithChild(userId);
		this.noOwnRolesList = this.roleService.findNotInRange(this.model.getRoles());
		return "editAuthorizePage";
	}
	
	public String saveOrUpdateUserAuthorize(){
		this.userService.saveOrUpdateAuthorize(userId,this.ownRoles);
		return "userListPageAction";
	}
	
	public String clearAuthorize(){
		this.userService.clearAuthorize(userId);
		return "userListPageAction";
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Role> getNoOwnRolesList() {
		return noOwnRolesList;
	}

	public void setNoOwnRolesList(List<Role> noOwnRolesList) {
		this.noOwnRolesList = noOwnRolesList;
	}

	public Integer[] getNoOwnRolesIds() {
		return noOwnRolesIds;
	}

	public void setNoOwnRolesIds(Integer[] noOwnRolesIds) {
		this.noOwnRolesIds = noOwnRolesIds;
	}

	public Integer[] getOwnRoles() {
		return ownRoles;
	}

	public void setOwnRoles(Integer[] ownRoles) {
		this.ownRoles = ownRoles;
	}

}
