package com.kelvin.test;

import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Service;
import com.kelvin.util.HibernateUtil;

public class TestManyToOne {

	@Test
	public void test1() {
		Session session = HibernateUtil.getSession();
		Service service = (Service)session.get(Service.class, 1);
		System.out.println(service.getOsUserName());
		System.out.println("----------------------");
		System.out.println(service.getAccount().getId() + " " + service.getAccount().getIdcardNo());
	}
}
