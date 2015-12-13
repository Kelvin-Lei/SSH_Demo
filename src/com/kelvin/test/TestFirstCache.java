package com.kelvin.test;

import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Emp;
import com.kelvin.util.HibernateUtil;

public class TestFirstCache {

	/**
	 * 用同一个Session查询同一条数据2次,
	 * 如果只查询一次数据库，则验证了一级缓存的存在
	 */
	@Test
	public void test1() {
		Session session = HibernateUtil.getSession();
		Emp e1 = (Emp)session.get(Emp.class, 1);
		System.out.println(e1.getName());
		System.out.println("----------------------");
		
		Emp e2 = (Emp)session.get(Emp.class, 1);
		System.out.println(e2.getName());
		session.close();
		
		System.out.println("======================");
	}
	
	/**
	 * 用2个不同的session，分别查询同一条数据，
	 * 如果查询2次数据库，则验证了一级缓存是Session独享的。
	 */
	@Test
	public void test2() {
		Session session1 = HibernateUtil.getSession();
		Emp e1 = (Emp)session1.get(Emp.class, 2);
		System.out.println(e1.getName());
		System.out.println("----------------------");
		
		Session session2 = HibernateUtil.getSession();
		Emp e2 = (Emp)session2.get(Emp.class, 2);
		System.out.println(e2.getName());
		
		session1.close();
		session2.close();
		
		System.out.println("======================");
	}
	
	/**
	 * 验证缓存管理的方法evict
	 */
	@Test
	public void test3() {
		System.out.println("===========验证缓存管理的方法evict===========");
		
		Session session = HibernateUtil.getSession();
		Emp e1 = (Emp)session.get(Emp.class, 1);
		System.out.println(e1.getName());
		session.evict(e1);
		Emp e2 = (Emp)session.get(Emp.class, 1);
		System.out.println(e2.getName());
		session.close();
		
		System.out.println("=============================================");
	}
	
	/**
	 * 验证缓存管理的方法clear
	 */
	@Test
	public void test4() {
		System.out.println("===========验证缓存管理的方法clear===========");
		
		Session session = HibernateUtil.getSession();
		Emp e1 = (Emp)session.get(Emp.class, 1);
		System.out.println(e1.getName());
		session.clear();
		Emp e2 = (Emp)session.get(Emp.class, 1);
		System.out.println(e2.getName());
		session.close();
		
		System.out.println("=============================================");
	}
}
