package ecommerce.rmall.service;

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;

public interface ICustomerService {

	Customer create(Customer customer);
	Customer activate(String username, String activateCode);
	Customer signIn(String username, String password);
	Customer validate(String username, String captcha);
	Customer signOut(String sessionKey);
	void update(String sessionKey, Customer customer);
	void update(Customer customer);
	Customer findByUsername(String username);
	Page<Customer> findCustomer(String query);

	Page<Coupon> coupons(String sessionKey, int pageNumber);
}
