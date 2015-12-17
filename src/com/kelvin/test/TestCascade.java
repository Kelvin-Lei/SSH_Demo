package com.kelvin.test;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.kelvin.entity.Account;
import com.kelvin.entity.Service;
import com.kelvin.util.HibernateUtil;

public class TestCascade {

	/**
	 * 级联添加
	 */
	@Test
	public void test1() {
		//模拟要添加的账务账号
		Account a1 = new Account();
		a1.setLoginName("gg");
		a1.setLoginPassword("123");
		a1.setRealName("gg");
		a1.setIdcardNo("1201349482728230");
		a1.setStatus("0");
		a1.setTelephone("110");
		
		//模拟要添加的业务账号
		Service s1 = new Service();
		s1.setAccount(a1);
		s1.setOsUserName("gg1");
		s1.setLoginPassword("123");
		s1.setUnixHost("192.168.1.1");
		s1.setCostId(5);
		s1.setStatus("0");
		
		Service s2 = new Service();
		s2.setAccount(a1);
		s2.setOsUserName("gg2");
		s2.setLoginPassword("123");
		s2.setUnixHost("192.168.1.2");
		s2.setCostId(5);
		s2.setStatus("0");
		
		//将业务账号与账务账号关联
		a1.setServices(new HashSet<Service>());
		a1.getServices().add(s1);
		a1.getServices().add(s2);
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(a1);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 级联修改
	 */
	@Test
	public void test2() {
		Session session = HibernateUtil.getSession();
		//查询出要修改的账务账号
		Account account = (Account)session.get(Account.class, 2);
		//模拟对账务账号的修改
		account.setLoginName("pp");
		Set<Service> services = account.getServices();
		for (Service service : services) {
			//模拟对业务账号的修改
			service.setLoginPassword("pp");
		}
		Transaction ts = session.beginTransaction();
		try {
			session.update(account);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		} finally {
			session.close();
		}
	}
}
