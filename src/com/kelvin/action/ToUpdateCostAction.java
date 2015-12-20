package com.kelvin.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kelvin.dao.ICostDao;
import com.kelvin.entity.Cost;

/**
 * 
 * 加载修改数据
 *
 */
@Controller
@Scope("prototype")
public class ToUpdateCostAction {

	@Resource
	private ICostDao costDao;
	
	private int id;
	private Cost cost;
	
	public String load() {
		//costs = costDao.findAll();
		
		//costs = costDao.findByPage(page, pageSize);
		//totalPage = costDao.findTotalPage(pageSize);
		
		cost = costDao.findById(id);
		
		return "success";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
}
