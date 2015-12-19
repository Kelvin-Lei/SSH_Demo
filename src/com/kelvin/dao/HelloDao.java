package com.kelvin.dao;

import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {

	public String say() {
		return "Hello, Spring整合Struts2.";
	}
}
