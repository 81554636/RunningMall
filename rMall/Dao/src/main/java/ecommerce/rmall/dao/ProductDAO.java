package ecommerce.rmall.dao;

import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.Product;

public class ProductDAO extends DaoSupport {
	
	public Product findByID(int identity){
		return super.get(Product.class, identity);
	}
	
	/***
	 * support Query Cache
	 * @param identity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByIDs(int[] identity){
		Integer[] ids = new Integer[identity.length];
		for(int i=0; i<identity.length; i++)
			ids[i] = identity[i];
		
		Query query = super.getSession().createQuery("from Product where id in (:pList)");
		query.setCacheable(true);
    	query.setParameterList("pList", ids);
    	return query.list();
	}
}