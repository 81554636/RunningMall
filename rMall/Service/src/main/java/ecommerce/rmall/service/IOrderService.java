package ecommerce.rmall.service;

import java.util.Set;

import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;

public interface IOrderService {
	
	Order place(Delivery delivery, Set<OrderItem> items, int customerID);
	Order query(int orderID);
}
