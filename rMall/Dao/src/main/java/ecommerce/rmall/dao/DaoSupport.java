package ecommerce.rmall.dao;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ecommerce.rmall.domain.Page;

public class DaoSupport {

	@Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void save(Object obj) {
        getSession().save(obj);
    }

    @SuppressWarnings("unchecked")
	protected <T> T get(Class<T> clazz, Integer id) {
        return (T) getSession().get(clazz, id);
    }
    
    protected void update(Object obj) {
        //getSession().update(obj);
        getSession().merge(obj);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected void delete(Class clazz, Integer id) {
        getSession().delete(get(clazz, id));
    }

    protected void delete(Object obj) {
        getSession().delete(obj);
    }
    
    @SuppressWarnings("unchecked")
	protected <T> List<T> findByProperty(Class<T> clazz, String property, Object value) {
        return getSession().createCriteria(clazz).add(eq(property, value)).list();
    }

    @SuppressWarnings("unchecked")
	protected <T> List<T> findAll(Class<T> clazz) {
        return getSession().createCriteria(clazz).list();
    }
    
    @SuppressWarnings("unchecked")
	protected <T> List<T> queryByHql(String hql, String[] params, Object[] values){
    	
    	Query query = this.getSession().createQuery(hql);
		for(int i=0; i<params.length; i++)
			query.setParameter(params[i], values[i]);
		return query.list();
    }
    
    /***
     * @param hql 举例:from Class where name=?
     * @param params 
     * @param page
     * @return
     */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForList(String hql, String[] params, Object[] values, Page<T> page) {  
    	  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        Query queryCount = sessionFactory.getCurrentSession().createQuery("select count(id) " + hql);
        for(int pos=0; pos<params.length; pos++){
        	query.setParameter(params[pos], values[pos]);
        	queryCount.setParameter(params[pos], values[pos]);
        }
        
        int totalCount = ((Long) queryCount.uniqueResult()).intValue();
        page.setTotalCount(totalCount);
        
        query.setFirstResult(page.getFirstIndex());  
        query.setMaxResults(page.getPageSize());
        
        return query.list();
    }
    
	/***
     * @param hql 举例:from Class where name=?
     * @param params 
     * @param page
     * @return
     */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForListWithCache(String hql, String[] params, Object[] values, Page<T> page) {  
  	  
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setCacheable(true);
        Query queryCount = sessionFactory.getCurrentSession().createQuery("select count(id) " + hql);
        queryCount.setCacheable(true);
        
        for(int pos=0; pos<params.length; pos++){
        	query.setParameter(params[pos], values[pos]);
        	queryCount.setParameter(params[pos], values[pos]);
        }
        
        int totalCount = ((Long) queryCount.uniqueResult()).intValue();
        page.setTotalCount(totalCount);
        
        query.setFirstResult(page.getFirstIndex());  
        query.setMaxResults(page.getPageSize());
        
        return query.list();
    }
}