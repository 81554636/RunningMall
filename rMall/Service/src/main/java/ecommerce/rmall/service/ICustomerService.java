package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;

public interface ICustomerService {
	void saveCustomer(Customer customer);
	List<Customer> findAll();
	Order place(Order order, long customerID);
	Order query(long orderID);
}
