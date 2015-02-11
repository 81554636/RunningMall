package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Customer;

public interface ICustomerService {
	void saveCustomer(Customer customer);
	List<Customer> findAll();
}
