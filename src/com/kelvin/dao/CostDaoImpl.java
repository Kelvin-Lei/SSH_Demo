package com.kelvin.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kelvin.entity.Cost;

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
	
	public List<Cost> findByPage(final int page, final int pageSize) {
		/*
		 * executeFind方法需传入接口对象，该接口对象的doInHibernate方法会
		 * 在执行查询时自动被Spring调用，并且doInHibernate方法的返回值将作
		 * 为最终的结果返回
		 */
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			/*
			 * doInHibernate方法类似于回调函数，是在executeFind方法的内部被调用的。
			 * 该方法中可以直接使用session，这个session由Spring负责管理，不需要我们
			 * 创建和关闭
			 */
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "from Cost";
				Query query = session.createQuery(hql);
				/*
				 * 设置分布参数，注意在内层函数中调用外层函数的参数，
				 * 要求外层函数的参数必须为final的，因此需要将page,pageSize设置为final
				 */
				query.setFirstResult((page - 1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	public int findTotalPage(int pageSize) {
		String hql = "select count(*) from Cost";
		List<Object> list = getHibernateTemplate().find(hql);
		//查询出总行数
		int rows = Integer.valueOf(list.get(0).toString());
		//根据总行数计算总页数
		if (rows % pageSize == 0) {
			return rows/pageSize;
		} else {
			return rows/pageSize + 1;
		}
	}
	
	public Cost findById(int id) {
		//return (Cost)getHibernateTemplate().get(Cost.class, id);
		
		//使用延迟加载的方法实现
		return (Cost)getHibernateTemplate().load(Cost.class, id);
	}
	
	public void save(Cost cost) {
		if(cost == null) {
			return;
		}
		getHibernateTemplate().save(cost);
	}
	
	public void update(Cost cost) {
		if(cost == null) {
			return;
		} 
		getHibernateTemplate().update(cost);
	}
	
	public void delete(int id) {
		Cost c = new Cost();
		c.setId(id);
		getHibernateTemplate().delete(c);
	}
}
