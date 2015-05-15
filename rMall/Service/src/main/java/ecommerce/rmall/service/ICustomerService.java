package ecommerce.rmall.service;

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;

public interface ICustomerService {

	Customer create(Customer customer);
	Customer validate(String username, String captcha);
	Customer activate(String username, String activateCode);
	void initResetPassword(String username);
	Customer resetPassword(String username, String activateCode, String password);
	
	Customer signIn(String username, String password);
	Customer signOut(String sessionKey);
	
	void update(String sessionKey, Customer customer);
	Customer update(Customer customer);
	Customer findByUsername(String username);
	Page<Customer> findCustomer(String query);

	Page<Coupon> coupons(String sessionKey, int pageNumber);
}
