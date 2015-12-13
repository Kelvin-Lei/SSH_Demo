package com.kelvin.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Emp;
import com.kelvin.util.HibernateUtil;

public class TestLazy {

	/**
	 * 验证load方法是延迟加载的
	 */
	@Test
	public void test1() {
		System.out.println("============验证load方法是延迟加载的============");
		
		Session session = HibernateUtil.getSession();
		//load方法并没有触发访问数据库
		Emp emp = (Emp)session.load(Emp.class, 1);
		System.out.println("-------------------------------");
		//使用emp对象时才真正访问数据库
		System.out.println(emp.getName());
		session.close();
	}
	
	/**
	 * 验证iterate方法是延迟加载的
	 */
	@Test
	public void test2() {
		System.out.println("============验证iterate方法是延迟加载的============");
		
		String hq1 = "from Emp";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hq1);
		//iterate方法访问了数据库，但只查询了ID列
		Iterator<Emp> it = query.iterate();
		System.out.println("---------------------------------");
		while (it.hasNext()) {
			Emp emp = it.next();
			//使用emp对象时才将其他列全部加载
			System.out.println(emp.getName());
		}
		session.close();
	}
}
