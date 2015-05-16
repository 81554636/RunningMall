package ecommerce.rmall.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Specification;

public class SpecificationDAO extends DaoSupport implements IPagination<Specification> {
	
	public Specification findByID(int identity){
		return super.get(Specification.class, identity);
	}
	
	/***
	 * support Query Cache
	 * @param identity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Specification> findByIDs(int[] identity){
		
		Integer[] ids = new Integer[identity.length];
		for(int i=0; i<identity.length; i++)
			ids[i] = identity[i];
		Query query = super.getSession().createQuery("from Specification where id in (:pList)");
		query.setCacheable(true);
    	query.setParameterList("pList", ids);
    	List<Specification> result = query.list();
    	
    	Map<Integer, Specification> rtn = new HashMap<Integer, Specification>();
    	for(Specification prod : result)
    		rtn.put(prod.getId(), prod);
    	return rtn;
	}

	@Override
	public Page<Specification> findWithPage(int pageNumber) {
		
		Page<Specification> page = new Page<Specification>();
		page.setCurrentPage(pageNumber);
		List<Specification> rtn = super.queryForListWithCache(
				"from Product where valid=true", 
				new String[]{}, 
				new Object[]{}, 
				page);
		page.setDataList(rtn);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<Specification> listAll(){
		Query query = super.getSession().createQuery("from Specification");
		query.setCacheable(true);
		return query.list();
	}

	@Override
	public Page<Specification> findByHQLWithPage(String hql, String[] params, Object[] values, int pageNumber) {
		
		Page<Specification> page = new Page<Specification>();
		page.setCurrentPage(pageNumber);
		List<Specification> rtn = super.queryForListWithCache("from Specification", params, values, page);
		page.setDataList(rtn);
		return page;
	}
}
