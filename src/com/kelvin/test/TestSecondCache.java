package com.kelvin.test;

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
}
