package ecommerce.rmall.service;

import ecommerce.rmall.domain.Order;

public interface IOrderService {
	
	Order place(Order order, int customerID);
	Order query(int orderID);
}
