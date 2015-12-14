package com.kelvin.test;

import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.kelvin.entity.Account;
import com.kelvin.entity.Service;
import com.kelvin.util.HibernateUtil;

public class TestOneToMany {

	@Test
	public void test1() {
		Session session = HibernateUtil.getSession();
		Account account = (Account)session.get(Account.class, 1);
		System.out.println(account.getIdcardNo() + " , " + account.getRealName() + " , " + account.getBirthDate());
		
		System.out.println("--------------------------------------");
		
		Set<Service> services = account.getServices();
		System.out.println(services.getClass().getName());
		for (Service service : services) {
			System.out.println(service.getOsUserName());
		}
		
		session.close();
	}
}
