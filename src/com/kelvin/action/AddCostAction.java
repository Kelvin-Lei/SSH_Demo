package com.kelvin.action;

import java.sql.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kelvin.dao.ICostDao;
import com.kelvin.entity.Cost;

@Controller
@Scope("prototype")
public class AddCostAction {

	@Resource
	private ICostDao costDao;
	
	private Cost cost;
	
	public String add() {
		//初始化默认值
		initDefaultValue(cost);
		costDao.save(cost);
		
		return "success";
	}
	
	/**
	 * 初始化默认值
	 */
	private void initDefaultValue(Cost cost) {
		//状态默认为暂停态
		cost.setStatus("1");
		//创建时间默认为当前系统时间
		cost.setCreateDate(new Date(System.currentTimeMillis()));
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
}
