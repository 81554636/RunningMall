package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.CouponDAO;
import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.notify.INotify;
import ecommerce.rmall.service.ICustomerService;
import ecommerce.rmall.utils.RandomCode;

public class CustomerService implements ICustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerDAO dao;
	private CouponDAO couponDao;
	public void setCustomerDao(CustomerDAO dao){ this.dao = dao; }
	public void setCouponDao(CouponDAO dao){ this.couponDao = dao; }
	
	private INotify notify;
	public void setNotify(INotify notify){ this.notify = notify; }

	@Override
	public Customer create(Customer customer) {
		
		String sessionKey = UUID.randomUUID().toString();
		customer.setCreateDate(new Date());
		customer.setJoinActivity(true);
		customer.setActivateCode(RandomCode.obtainRandomCode());
		if(null != customer.getCredential())
			customer.getCredential().setSessionKey(sessionKey);
		
		logger.info("save customer[{}] to DATABASE", customer.getCredential().getUsername());
		Customer rtn = this.dao.save(customer);
		
		logger.info("ignore sending ACTIVATE-CODE[{}] to customer {}", customer.getActivateCode(), customer.getPhone());
		this.notify.sendMessage(customer.getPhone(), customer.getActivateCode());
		return rtn;
	}
	
	@Override
	public Customer activate(String username, String activateCode) {

		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer && activateCode.equals(customer.getActivateCode())){
			customer.setActive(true);
			this.dao.update(customer);
			logger.info("activate customer {}", customer.getCredential().getUsername());
		}
		return customer;
	}
	
	@Override
	public Customer validate(String username, String captcha) {

		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer && customer.getActivateCode().equals(captcha)){
			return customer;
		}
		return null;
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
	public void update(Customer updateCustomer) {
		
		Customer customer = this.dao.findByID(updateCustomer.getId());
		if( null != customer ){
			
			customer.setAddress(updateCustomer.getAddress());
			customer.setName(updateCustomer.getName());
			customer.getCredential().setPassword(
					updateCustomer.getCredential()==null?"":updateCustomer.getCredential().getPassword());
		}
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
