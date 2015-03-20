package ecommerce.rmall.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.CountByDate;
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
		List<Order> rtn = super.queryForList(
				"from Order order by id desc", 
				new String[]{}, 
				new Object[]{}, 
				page);
		page.setDataList(rtn);
		return page;
	}

	@Override
	public Page<Order> findByHQLWithPage(String hql, String[] params, Object[] values, int pageNumber) {
		
		Page<Order> page = new Page<Order>();
		page.setCurrentPage(pageNumber);
		List<Order> rtn = super.queryForList(hql, params, values, page);
		page.setDataList(rtn);
		return page;
	}

	public Order findByHQL(String hql, String[] params, Object[] values){
		
		List<Order> result = super.queryByHql(hql, params, values);
		if(null != result && result.size()>0)
			return (Order)result.get(0);
		else
			return null;
	}
	
	public List<CountByDate> count(){
		
		List<CountByDate> rtn = new ArrayList<CountByDate>();
		Query query = super.getSession().getNamedQuery("countGroupByDate");
		List<Object[]> result = query.list();
		for(int i=0; i<result.size(); i++){
			Object[] objs = (Object[])result.get(i);
			int count = ((java.math.BigInteger)objs[0]).intValue();
			String date = ((java.util.Date)objs[1]).toString();
			rtn.add(new CountByDate(count, date));
		}
		return rtn;
	}
}
