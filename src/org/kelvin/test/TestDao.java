package org.kelvin.test;

import java.util.List;

import org.junit.Test;
import org.kelvin.dao.ICostDao;
import org.kelvin.entity.Cost;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDao {

	private String conf = "applicationContext.xml";
	
	@Test
	public void test1() {
		System.out.println("==========FindAll==========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		ICostDao dao = (ICostDao)ctx.getBean("costDaoImpl");
		List<Cost> list = dao.findAll();
		for(Cost cost : list) {
			System.out.println(cost.getId() + " " + cost.getName());
		}
	}
	
	@Test
	public void test2() {
		System.out.println("==========FindById==========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		ICostDao dao = (ICostDao)ctx.getBean("costDaoImpl");
		Cost cost = dao.findById(1);
		System.out.println(cost.getId() + " " + cost.getName());
	}

	@Test
	public void test3() {
		System.out.println("==========Save==========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		ICostDao dao = (ICostDao)ctx.getBean("costDaoImpl");
		Cost cost = new Cost();
		cost.setName("aaa");
		cost.setBaseDuration(20);
		cost.setBaseCost(2.0);
		cost.setUnitCost(0.2);
		cost.setCostType("1");
		cost.setStatus("0");
		dao.save(cost);
	}
	
	@Test
	public void test4() {
		System.out.println("==========Update==========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		ICostDao dao = (ICostDao)ctx.getBean("costDaoImpl");
		Cost cost = dao.findById(1);
		cost.setName("bbb");
		dao.update(cost);
	}
	
	@Test
	public void test5() {
		System.out.println("==========Delete==========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		ICostDao dao = (ICostDao)ctx.getBean("costDaoImpl");
		dao.delete(3);
	}
}
