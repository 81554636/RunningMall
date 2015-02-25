package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Order;

public class OrderDAO extends DaoSupport {
	
	public void save(Order order){
		super.save(order);
	}
	
	public Order findByID(int identity){
		return super.get(Order.class, identity);
	}
}
