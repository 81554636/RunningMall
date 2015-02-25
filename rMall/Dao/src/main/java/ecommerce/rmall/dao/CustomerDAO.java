package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Customer;

public class CustomerDAO extends DaoSupport {
	
	public Customer findByID(int identity){
		return super.get(Customer.class, identity);
	}
}
