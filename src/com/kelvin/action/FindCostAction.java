package com.kelvin.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kelvin.dao.ICostDao;
import com.kelvin.entity.Cost;

@Controller
@Scope("prototype")
public class FindCostAction {

	@Resource
	private ICostDao costDao;
	
	private List<Cost> costs;
	
	private int page = 1;
	private int pageSize;
	private int totalPage;
	
	public String load() {
		//costs = costDao.findAll();
		
		costs = costDao.findByPage(page, pageSize);
		totalPage = costDao.findTotalPage(pageSize);
		
		return "success";
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
