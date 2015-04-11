package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.UUID;

import ecommerce.rmall.dao.CouponDAO;
import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.service.ICustomerService;
import ecommerce.rmall.utils.CredentialHelper;
import ecommerce.rmall.utils.RandomCode;

public class CustomerService implements ICustomerService {
	
	private CustomerDAO dao;
	private CouponDAO couponDao;
	public void setCustomerDao(CustomerDAO dao){ this.dao = dao; }
	public void setCouponDao(CouponDAO dao){ this.couponDao = dao; }

	@Override
	public Customer create(Customer customer) {
		
		String sessionKey = UUID.randomUUID().toString();
		customer.setCreateDate(new Date());
		customer.setJoinActivity(true);
		customer.setActivateCode(RandomCode.obtainRandomCode());
		if(null != customer.getCredential())
			customer.getCredential().setSessionKey(sessionKey);
		return this.dao.save(customer);
	}
	
	@Override
	public Customer activate(String username, String activateCode) {

		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer && activateCode.equals(customer.getActivateCode())){
			customer.setActive(true);
			this.dao.update(customer);
		}
		return customer;
	}

	@Override
	public Customer signIn(String username, String password) {

		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer){

			if(customer.getCredential().getPassword().equals(password)){
				String sessionKey = UUID.randomUUID().toString();
				customer.getCredential().setSessionKey(sessionKey);
				this.dao.update(customer);
			} else
				customer = null;
		}
		return customer;
	}

	@Override
	public Customer signOut(String sessionKey) {
		
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.dao.findByHQL(hql, new String[]{"sessionKey"}, new Object[]{sessionKey});
		if(null != customer){
			customer.getCredential().setSessionKey(null);
			this.dao.update(customer);
		}
		return customer;
	}
	
	@Override
	public Customer findByUsername(String username) {
		
		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		return customer;
	}

	@Override
	public void update(String sessionKey, Customer customer) {
		
		this.dao.update(customer);
	}

	@Override
	public Page<Coupon> coupons(String sessionKey, int pageNumber) {

		String hql = "from Coupon where owner.credential.sessionKey=:sessionKey";
		return this.couponDao.findByHQLWithPage(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey}, pageNumber);
	}
	
	@Override
	public Page<Customer> findCustomer(String query) {
		
		String hql = "from Customer where name like :Param OR phone like :Param";
		String param = "%" + query + "%";
		return this.dao.findByHQLWithPage(hql, new String[]{"Param"}, new Object[]{param}, 1);
	}
}
