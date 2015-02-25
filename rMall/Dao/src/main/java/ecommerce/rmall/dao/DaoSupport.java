package ecommerce.rmall.dao;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}