package com.kelvin.dao;

import java.util.List;

import com.kelvin.entity.Cost;

public interface ICostDao {

	List<Cost> findAll();
	
	/**
	 * 查询某页资费数据
	 * @param page 页码
	 * @param pageSize 页容量
	 * @return
	 */
	List<Cost> findByPage(int page, int pageSize);
	
	/**
	 * 查询总页数
	 * @param pageSize
	 * @return
	 */
	int findTotalPage(int pageSize);
	
	Cost findById(int id);
	
	void save(Cost cost);
	
	void update(Cost cost);
	
	void delete(int id);
}
