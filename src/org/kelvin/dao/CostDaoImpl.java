package org.kelvin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.kelvin.entity.Cost;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CostDaoImpl extends HibernateDaoSupport implements ICostDao{

	@Resource
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	public List<Cost> findAll() {
		String hql = "from Cost";
		return getHibernateTemplate().find(hql);
	}
	
	public Cost findById(int id) {
		return (Cost)getHibernateTemplate().get(Cost.class, id);
	}
	
	public void save(Cost cost) {
		getHibernateTemplate().save(cost);
	}
	
	public void update(Cost cost) {
		getHibernateTemplate().update(cost);
	}
	
	public void delete(int id) {
		Cost c = new Cost();
		c.setId(id);
		getHibernateTemplate().delete(c);
	}
}
