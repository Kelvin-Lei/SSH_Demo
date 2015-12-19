package com.kelvin.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Emp;
import com.kelvin.util.HibernateUtil;

public class TestSecondCache {

	/**
	 * 二级缓存
	 */
	@Test
	public void test1() {
		System.out.println("=============二级缓存=============");
		
		Session session1 = HibernateUtil.getSession();
		Emp e1 = (Emp)session1.get(Emp.class, 1);
		System.out.println(e1.getName());
		
		System.out.println("----------------------------");
		
		// 通过SessionFactory将Emp从二级缓存中剔除掉
		HibernateUtil.getSessionFactory().evict(Emp.class);
		
		Session session2 = HibernateUtil.getSession();
		Emp e2 = (Emp)session2.get(Emp.class, 1);
		System.out.println(e2.getName());
		
		session1.close();
		session2.close();
	}
	
	/**
	 * 查询缓存
	 */
	@Test
	public void test2() {
		System.out.println("=============查询缓存=============");
		
		Session session = HibernateUtil.getSession();
		
		String hql = "from Emp";
		Query query = session.createQuery(hql);
		// 开启查询缓存
		query.setCacheable(true);
		List<Emp> emps = query.list();
		for(Emp emp : emps) {
			System.out.println(emp.getId() + " " + emp.getName());
		}
		System.out.println("------------------------");
		
		// 通过SessionFactory对二级缓存进行管理
		HibernateUtil.getSessionFactory().evictQueries();
		
		hql = "from Emp";
		query = session.createQuery(hql);
		// 开启查询缓存
		query.setCacheable(true);
		emps = query.list();
		for(Emp emp : emps) {
			System.out.println(emp.getId() + " " + emp.getName());
		}
		
		session.close();
	}
}
