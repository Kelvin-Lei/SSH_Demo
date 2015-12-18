package com.kelvin.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Service;
import com.kelvin.util.HibernateUtil;

public class TestHQL {

	/**
	 * 按条件查询
	 */
	@Test
	public void test1() {
		System.out.println("===========按条件查询==========");
		
		//注意：(hql)from是实体类名，而不是表名；同样，where后面是实体类属性名，而不是字段名；(注意大小)
		String hql = "from Service where unixHost=?"; 
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		query.setString(0, "192.168.1.1");
		List<Service> services = query.list();
		for (Service service : services) {
			System.out.println(service.getId() + " " + service.getUnixHost() + " " + service.getOsUserName());
		}
		session.close();
	}
	
	/**
	 * 查询一部分字段
	 */
	@Test
	public void test2() {
		System.out.println("===========查询一部分字段==========");
		
		String hql = "select id,unixHost,osUserName " + "from Service where unixHost=?";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		query.setString(0, "192.168.1.1");
		List<Object[]> services = query.list();
		for (Object[] service : services) {
			System.out.println(service[0] + " " + service[1] + " " + service[2]);
		}
		session.close();
	}
	
	/**
	 * HQL查询，分页查询
	 */
	@Test
	public void test3() {
		System.out.println("===========HQL查询，分页查询==========");
		
		int page = 1;
		int pageSize = 3;
		
		String hql = "from Service order by id";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		//追加分页参数设置
		int from = (page - 1) * pageSize;
		query.setFirstResult(from); //设置起点，从0开始
		query.setMaxResults(pageSize); //设置页容量
		List<Service> services = query.list();
		for (Service service : services) {
			System.out.println(service.getId() + " " + service.getUnixHost() + " " + service.getOsUserName());
		}
	}
	
	/**
	 * 查询总页数
	 */
	@Test
	public void test4() {
		System.out.println("===========查询总页数==========");
		
		int pageSize = 3;
		String hql = "select count(*) from Service";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		int rows = Integer.parseInt(query.uniqueResult().toString());
		int totalPages = 0;
		if (rows%pageSize == 0) {
			totalPages = rows/pageSize;
		} else {
			totalPages = rows/pageSize + 1;
		}
		System.out.println(totalPages);
		session.close();
	}
	
	/**
	 * 多表联合查询-对象方式关联
	 */
	@Test
	public void test5() {
		System.out.println("===========多表联合查询-对象方式关联==========");
		
		String hql = "select " +
					 "s.id,s.osUserName,a.id,a.realName,a.idcardNo " +
					 "from Service s,Account a " +
					 "where s.account.id = a.id";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			System.out.println(objs[0] + " " +
							   objs[1] + " " +
							   objs[2] + " " +
							   objs[3] + " " +
							   objs[4]);
		}
		session.close();
	}
	
	/**
	 * 多表联合查询-join方式关联
	 */
	@Test
	public void test6() {
		System.out.println("===========多表联合查询-join方式关联==========");
		
		String hql = "select " +
					 "s.id,s.osUserName,a.id,a.realName,a.idcardNo " +
					 "from Service s join s.account a ";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			System.out.println(objs[0] + " " +
							   objs[1] + " " +
							   objs[2] + " " +
							   objs[3] + " " +
							   objs[4]);
		}
		session.close();
	}
	
	/**
	 * 多表联合查询-select子句关联
	 */
	@Test
	public void test7() {
		System.out.println("===========多表联合查询-select子句关联==========");
		
		String hql = "select " +
					 "s.id,s.osUserName,s.unixHost,s.account.id,s.account.realName from Service s ";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			System.out.println(objs[0] + " " +
							   objs[1] + " " +
							   objs[2] + " " +
							   objs[3] + " " +
							   objs[4]);
		}
		session.close();
	}
}
