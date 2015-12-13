package com.kelvin.test;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.kelvin.entity.Emp;
import com.kelvin.util.HibernateUtil;

public class TestPersistent {

	/**
	 * 持久态对象存在于一级缓存中
	 */
	@Test
	public void test1() {
		System.out.println("=================持久态对象存在于一级缓存中=================");
		
		Emp e = new Emp();
		e.setName("张三");
		e.setAge(29);
		e.setMarry(false);
		e.setSalary(12000.0);
		e.setBirthday(Date.valueOf("1983-10-20"));
		e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(e);
			ts.commit();
		} catch (HibernateException e2) {
			e2.printStackTrace();
			ts.rollback();
		}
		
		Emp emp = (Emp)session.get(Emp.class, e.getId());
		System.out.println(emp.getId() + " " + emp.getName());
		
		session.close();
	}
	
	/**
	 * 持久态对象可以自动更新至数据库
	 */
	@Test
	public void test2() {
		System.out.println("=================持久态对象可以自动更新至数据库=================");
		
		Emp e = new Emp();
		e.setName("李四");
		e.setAge(29);
		e.setMarry(false);
		e.setSalary(12000.0);
		e.setBirthday(Date.valueOf("1983-10-20"));
		e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(e);
			e.setName("小李子");
			ts.commit();
		} catch (HibernateException e2) {
			e2.printStackTrace();
			ts.rollback();
		}
		session.close();
	}
	
	/**
	 * 持久态对象自动更新数据的时机
	 */
	@Test
	public void test3() {
		System.out.println("=================持久态对象自动更新数据的时机=================");
		
		Session session = HibernateUtil.getSession();
		Emp emp = (Emp)session.load(Emp.class, 1);
		emp.setName("贝贝");
		session.flush(); //同步但未提交事务
		session.close();
	}
}
