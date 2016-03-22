package com.legend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.legend.model.security.Right;
import com.legend.model.security.Role;

public class User extends BaseEntity {
	private Integer id;
	private String email;
	private String name;
	private String password;
	private String nickName;
	private Date regDate;
	private Set<Role> roles = new HashSet<Role>();
	private long[] rightSum;
	private boolean superAdmin;
	
	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void calculateRightSum() {
		for(Role role : roles){
			if(role.getRoleValue().equals("-1")){
				this.superAdmin = true;
				//释放资源
				roles = null;
				return ;
			}
			for(Right right : role.getRights())
				rightSum[right.getRightPos()] |= right.getRightCode();
		}
		
		//释放资源
		roles = null;
	}

	public boolean hashRight(Right right) {
		return (rightSum[right.getRightPos()] & right.getRightCode()) == 0 ? false : true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}

	
}
