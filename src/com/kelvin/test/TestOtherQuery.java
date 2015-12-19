package com.kelvin.test;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Service;
import com.kelvin.util.HibernateUtil;

public class TestOtherQuery {

	/**
	 * 使用SQL查询
	 */
	@Test
	public void test1() {
		System.out.println("===========使用SQL查询==========");
		
		String sql = "select * from service where unix_host=?";
		Session session = HibernateUtil.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, "ubuntu1");
		query.addEntity(Service.class);
		//采用Service类型封闭一条数据
		List<Service> list = query.list();
		for(Service service : list) {
			System.out.println(service.getId() + " " + service.getOsUserName() + " " + service.getUnixHost());
		}
		session.close();
	}
}
