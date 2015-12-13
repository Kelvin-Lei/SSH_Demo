package com.kelvin.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static {
		//加载Hibernate主配置文件
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		sessionFactory = conf.buildSessionFactory();
	}
	
	/**
	 * 创建Session
	 */
	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
	public static void main(String[] args) {
		System.out.println(getSession());
	}
}
