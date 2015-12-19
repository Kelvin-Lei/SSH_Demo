package org.kelvin.dao;

import java.util.List;

import org.kelvin.entity.Cost;

public interface ICostDao {

	List<Cost> findAll();
	
	Cost findById(int id);
	
	void save(Cost cost);
	
	void update(Cost cost);
	
	void delete(int id);
}
