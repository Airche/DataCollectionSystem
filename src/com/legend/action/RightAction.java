package com.legend.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.User;
import com.legend.model.security.Right;
import com.legend.service.RightService;

@Controller("rightAction")
@Scope("prototype")
public class RightAction extends BaseAction<Right> {
	
	private int rightId;
	private List<Right> allRights;
	
	@Resource(name="rightService")
	private RightService rightService;
	
	public String findAllRights(){
		this.allRights = this.rightService.findAllEntities();
		return "rightListPage";
	}
	
	public String toAddRightPage(){
		return "addRightPage";
	}
	
	public String toEditRightPage(){
		this.model = this.rightService.getEntity(Right.class, this.rightId);
		return "editRightPage";
	}
	
	public String deleteRight(){
		Right right = new Right();
		right.setId(this.rightId); 
		this.rightService.deleteEntity(right);
		return "rightListPageAction";
	}
	
	public String batchUpdateRights(){
		this.rightService.batchUpdateRights(allRights);
		return "rightListPageAction";
	}
	
	public String saveOrUpdateRight(){
		this.rightService.saveOrUpdateRight(this.model);
		return "rightListPageAction";
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	
}
