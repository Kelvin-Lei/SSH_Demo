package com.kelvin.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kelvin.dao.ICostDao;

@Controller
@Scope("prototype")
public class DeleteCostAction {

	@Resource
	private ICostDao costDao;
	
	private int id;
	
	public String delete() {
		costDao.delete(id);
		
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
