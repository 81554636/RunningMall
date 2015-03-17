package ecommerce.rmall.dao;

import java.util.List;

import ecommerce.rmall.domain.Customer;

public class CustomerDAO extends DaoSupport {
	
	public Customer findByID(int identity){
		return super.get(Customer.class, identity);
	}
	
	public Customer save(Customer customer){
		super.save(customer);
		return customer;
	}
	
	public Customer findByHQL(String hql, String[] params, Object[] values){
		
		List<Customer> result = super.queryByHql(hql, params, values);
		if(null != result && result.size()>0)
			return (Customer)result.get(0);
		else
			return null;
	}
}
