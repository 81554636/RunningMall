package ecommerce.rmall.dao;

import java.util.List;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;

public class OrderDAO extends DaoSupport implements IPagination<Order>{
	
	public void save(Order order){
		super.save(order);
	}
	public void update(Order order){
		super.update(order);
	}
	
	public Order findByID(int identity){
		return super.get(Order.class, identity);
	}
	
	@Override
	public Page<Order> findWithPage(int pageNumber){
		
		Page<Order> page = new Page<Order>();
		page.setCurrentPage(pageNumber);
		List<Order> rtn = super.queryForList("from Order", new Object[]{}, page);
		page.setDataList(rtn);
		return page;
	}

	@Override
	public Page<Order> findByHQLWithPage(String hql, Object[] params, int pageNumber) {
		
		Page<Order> page = new Page<Order>();
		page.setCurrentPage(pageNumber);
		List<Order> rtn = super.queryForList(hql, params, page);
		page.setDataList(rtn);
		return page;
	}
	
	public Order findByHQL(String hql, Object[] params){
		
		List<Order> result = super.queryByHql(hql, params);
		if(null != result && result.size()>0)
			return (Order)result.get(0);
		else
			return null;
	}
}
