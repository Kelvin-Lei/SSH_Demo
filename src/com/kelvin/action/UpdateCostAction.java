package com.kelvin.action;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kelvin.dao.ICostDao;
import com.kelvin.entity.Cost;

@Controller
@Scope("prototype")
public class UpdateCostAction {

	@Resource
	private ICostDao costDao;
	
	private Cost cost;
	
	public String update() {
		//costDao.update(cost);
		
		//查询出要修改的原始对象，该对象为持久态
		Cost c = costDao.findById(cost.getId());
		//将传入对象的属性复制到原始对象上，忽视不需修改的列
		BeanUtils.copyProperties(cost, c, new String[] {"status", "createTime", "startTime"});
		/*
		 * 对持久态对象更新，由于映射关系文件中设置了动态更新，
		 * 因此持久对象中值为null的列将不被更新
		 */
		costDao.update(c);
		
		return "success";
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
}
