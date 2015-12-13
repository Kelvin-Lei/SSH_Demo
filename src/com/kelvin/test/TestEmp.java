package com.kelvin.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.kelvin.entity.Emp;
import com.kelvin.util.HibernateUtil;

public class TestEmp {

	/**
	 * 新增emp
	 */
	@Test
	public void add() {
		Emp e = new Emp();
		e.setName("ggg");
		e.setAge(29);
		e.setMarry(false);
		e.setSalary(8000.0);
		e.setBirthday(Date.valueOf("1990-01-01"));
		e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		
		//获取session
		Session session = HibernateUtil.getSession();
		//开启事务
		Transaction trx = session.beginTransaction();
		try {
			//执行新增
			session.save(e);
			//提交事务
			trx.commit();
		} catch (HibernateException e2) {
			e2.printStackTrace();
			//回滚事务
			trx.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 根据ID查询emp
	 */
	@Test
	public void findById() {
		Session session = HibernateUtil.getSession();
		Emp emp = (Emp)session.get(Emp.class, 1);
		System.out.println(emp.getName());
		System.out.println(emp.getBirthday());
		System.out.println(emp.getLastLoginTime());
		session.close();
	}
	
	/**
	 * 修改emp
	 */
	@Test
	public void update() {
		Session session = HibernateUtil.getSession();
		//查询要修改的数据
		Emp emp = (Emp)session.get(Emp.class, 1);
		//开启事务
		Transaction trx = session.beginTransaction();
		try {
			//模拟修改数据
			emp.setName("eee");
			emp.setAge(20);
			session.update(emp);
			trx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			//回滚事务
			trx.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 删除emp
	 */
	@Test
	public void delete() {
		Session session = HibernateUtil.getSession();
		Emp emp = (Emp)session.get(Emp.class, 301);
		Transaction ts = session.beginTransaction();
		try {
			session.delete(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 查询全部emp
	 */
	@Test
	public void findAll() {
		String hq1 = "from Emp";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hq1);
		List<Emp> emps = query.list();
		for (Emp emp : emps) {
			System.out.println(emp.getName());
		}
		session.close();
	}
}
