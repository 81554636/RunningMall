package ecommerce.rmall.dao;

import java.util.List;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;

public class CustomerDAO extends DaoSupport implements IPagination<Customer> {
	
	public Customer findByID(int identity){
		return super.get(Customer.class, identity);
	}
	
	public Customer save(Customer customer){
		super.save(customer);
		return customer;
	}
	
	public void update(Customer customer){
		super.update(customer);
	}
	
	public Customer findByHQL(String hql, String[] params, Object[] values){
		
		List<Customer> result = super.queryByHql(hql, params, values);
		if(null != result && result.size()>0)
			return (Customer)result.get(0);
		else
			return null;
	}

	@Override
	public Page<Customer> findWithPage(int pageNumber) {
		
		Page<Customer> page = new Page<Customer>();
		page.setCurrentPage(pageNumber);
		List<Customer> rtn = super.queryForList(
				"from Customer order by id desc", 
				new String[]{}, 
				new Object[]{}, 
				page);
		page.setDataList(rtn);
		return page;
	}

	@Override
	public Page<Customer> findByHQLWithPage(String hql, String[] params,
			Object[] values, int pageNumber) {
		
		Page<Customer> page = new Page<Customer>();
		page.setCurrentPage(pageNumber);
		List<Customer> rtn = super.queryForList(hql, params, values, page);
		page.setDataList(rtn);
		return page;
	}
}
