package ecommerce.rmall.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<Integer, Product> findByIDs(int[] identity){
		
		Integer[] ids = new Integer[identity.length];
		for(int i=0; i<identity.length; i++)
			ids[i] = identity[i];
		Query query = super.getSession().createQuery("from Product where id in (:pList)");
		query.setCacheable(true);
    	query.setParameterList("pList", ids);
    	List<Product> result = query.list();
    	
    	Map<Integer, Product> rtn = new HashMap<Integer, Product>();
    	for(Product prod : result)
    		rtn.put(prod.getId(), prod);
    	return rtn;
	}

	@Override
	public Page<Product> findWithPage(int pageNumber) {
		
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(pageNumber);
		List<Product> rtn = super.queryForListWithCache(
				"from Product where valid=true", 
				new String[]{}, 
				new Object[]{}, 
				page);
		page.setDataList(rtn);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> listAll(){
		Query query = super.getSession().createQuery("from Product");
		query.setCacheable(true);
		return query.list();
	}

	@Override
	public Page<Product> findByHQLWithPage(String hql, String[] params, Object[] values, int pageNumber) {
		
		Page<Product> page = new Page<Product>();
		page.setCurrentPage(pageNumber);
		List<Product> rtn = super.queryForListWithCache("from Product where valid=true", params, values, page);
		page.setDataList(rtn);
		return page;
	}
}
