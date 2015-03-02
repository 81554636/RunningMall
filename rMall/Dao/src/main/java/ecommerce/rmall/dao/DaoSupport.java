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
    
    @SuppressWarnings("unchecked")
	protected <T> List<T> findByProperty(Class<T> clazz, String property, Object value) {
        return getSession().createCriteria(clazz).add(eq(property, value)).list();
    }

    @SuppressWarnings("unchecked")
	protected <T> List<T> findAll(Class<T> clazz) {
        return getSession().createCriteria(clazz).list();
    }

    protected void update(Object obj) {
        getSession().update(obj);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected void delete(Class clazz, Integer id) {
        getSession().delete(get(clazz, id));
    }

    protected void delete(Object obj) {
        getSession().delete(obj);
    }
    
    /***
     * @param hql 举例:from Class where name=?
     * @param params 
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
	protected <T> List<T> queryForList(String hql, Object[] params, Page<T> page) {  
    	  
        generatePageTotalCount(hql, params, page);
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        //setQueryParams(query, params);
        for(int pos=0; pos<params.length; pos++)
        	query.setParameter(pos, params[pos]);
        query.setFirstResult(page.getFirstIndex());  
        query.setMaxResults(page.getPageSize());  
        return query.list();  
    }
    
    /** 
     * 该方法会改变参数page的totalCount字段 
     *  
     * @param originHql 原始hql语句 
     * @param params 原始参数 
     * @param page 页面对象 
     */  
    private void generatePageTotalCount(String originHql, Object[] params, Page page) {
    	
        String generatedCountHql = "select count(*) " + originHql;  
        Query countQuery = sessionFactory.getCurrentSession().createQuery(generatedCountHql);  
        //setQueryParams(countQuery, params);
        for(int pos=0; pos<params.length; pos++)
        	countQuery.setParameter(pos, params[pos]);
        
        int totalCount = ((Long) countQuery.uniqueResult()).intValue();  
        page.setTotalCount(totalCount);  
    }  
}