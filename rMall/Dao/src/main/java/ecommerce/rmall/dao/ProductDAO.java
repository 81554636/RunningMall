package ecommerce.rmall.dao;

import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;

public class ProductDAO extends DaoSupport implements IPagination<Product> {
	
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

	@Override
	public Page<Product> findWithPage(int pageNumber) {
		
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(pageNumber);
		List<Product> rtn = super.queryForListWithCache("from Product", new Object[]{}, page);
		page.setDataList(rtn);
		return page;
	}

	@Override
	public Page<Product> findByHQLWithPage(String hql, Object[] params, int pageNumber) {
		
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(pageNumber);
		List<Product> rtn = super.queryForListWithCache("from Product", params, page);
		page.setDataList(rtn);
		return page;
	}
}
