package ecommerce.rmall.dao;

import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.Activity;

public class ActivityDAO extends DaoSupport {
	
	@SuppressWarnings("unchecked")
	public List<Activity> listValid(){
		
		String hql = "from Activity where valid=true order by id desc";
		Query query = super.getSession().createQuery(hql);
		query.setCacheable(true);
		return query.list();
	}
}
