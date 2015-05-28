package ecommerce.rmall.service.impl;

import java.util.Calendar;
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

		String hql = "from Customer where credential.username=:username";
		Customer existCustomer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{customer.getCredential().getUsername()});
		if( null != existCustomer ){
			
			if(existCustomer.isActive() == false){
				
				logger.info("CUSTOMER[{}] already exists", customer.getCredential().getUsername());
				
				Calendar lastModify = Calendar.getInstance();
				lastModify.setTime(existCustomer.getLastUpdate());
				lastModify.add(Calendar.MINUTE, 30);
				Calendar current = Calendar.getInstance();
				current.setTime(new Date());
				
				if(current.after(lastModify)){
					
					logger.info("updating new ACTIVATE-CODE to DATABASE");
					existCustomer.setLastUpdate(new Date());
					existCustomer.setActivateCode(RandomCode.obtainRandomCode());
					this.dao.update(existCustomer);
				}
			
				logger.info("sending ACTIVATE-CODE[{}] to CUSTOMER[{}]", existCustomer.getActivateCode(), existCustomer.getPhone());
				this.notify.sendMessage(existCustomer.getPhone(), existCustomer.getActivateCode());
			}
			
			return existCustomer;
		} else {

			customer.setCreateDate(new Date());
			customer.setLastUpdate(new Date());
			customer.setJoinActivity(true);
			customer.setActivateCode(RandomCode.obtainRandomCode());
			customer.setPhone(customer.getCredential().getUsername());
			
			logger.info("save CUSTOMER[{}] to DATABASE", customer.getCredential().getUsername());
			Customer rtn = this.dao.save(customer);
			
			logger.info("sending ACTIVATE-CODE[{}] to CUSTOMER[{}]", customer.getActivateCode(), customer.getPhone());
			this.notify.sendMessage(customer.getPhone(), customer.getActivateCode());
			return rtn;
		}
	}
	
	@Override
	public Customer activate(String sessionKey) {

		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.dao.findByHQL(hql, new String[]{"sessionKey"}, new Object[]{sessionKey});
		if(null != customer){
			
			customer.setActive(true);
			logger.info("activate CUSTOMER[{}]", customer.getCredential().getUsername());
			this.dao.update(customer);
		}
		return customer;
	}
	
	@Override
	public Customer validate(String username, String captcha) {

		String hql = "from Customer where credential.username=:username";
		logger.info("validate CUSTOMER[{}] by ACTIVATE-CODE[{}]", username, captcha);
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer && customer.getActivateCode().equals(captcha)){
			
			String sessionKey = UUID.randomUUID().toString();
			customer.getCredential().setSessionKey(sessionKey);
			this.dao.update(customer);
			return customer;
		}
		return null;
	}

	@Override
	public Customer signIn(String username, String password) {

		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer){

			if(null != customer.getCredential().getPassword() &&
					customer.getCredential().getPassword().equals(password)){
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
	public Customer update(String sessionKey, Customer customer) {
		
		logger.info("update CUSTOMER[{}] by SESSION-KEY[{}]", customer.getCredential().getUsername(), sessionKey);
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer pCustomer = this.dao.findByHQL(
				hql, new String[]{"sessionKey"}, new Object[]{sessionKey});
		if( null != customer ){
			
			pCustomer.setAddress(customer.getAddress());
			pCustomer.setName(customer.getName());
			pCustomer.setEmail(customer.getEmail());
			pCustomer.setImgUrl(customer.getImgUrl());
			pCustomer.getCredential().setPassword(
					customer.getCredential()==null?"":customer.getCredential().getPassword());
			pCustomer.setLastUpdate(new Date());
			this.dao.update(pCustomer);
		}
		return pCustomer;
	}
	
	@Override
	public Customer update(Customer updateCustomer) {
		
		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(
				hql, new String[]{"username"}, new Object[]{updateCustomer.getCredential().getUsername()});

		if( null != customer ){
			
			customer.setAddress(updateCustomer.getAddress());
			customer.setName(updateCustomer.getName());
			customer.setEmail(updateCustomer.getEmail());
			customer.setImgUrl(updateCustomer.getImgUrl());
			customer.getCredential().setPassword(
					updateCustomer.getCredential()==null?"":updateCustomer.getCredential().getPassword());
			customer.setLastUpdate(new Date());
			this.dao.update(customer);
		}
		return customer;
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
	
	@Override
	public void initResetPassword(String username) {
		
		String hql = "from Customer where credential.username=:username";
		Customer customer = this.dao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
		if(null != customer){
			
			Calendar lastModify = Calendar.getInstance();
			lastModify.setTime(customer.getLastUpdate());
			lastModify.add(Calendar.MINUTE, 30);
			Calendar current = Calendar.getInstance();
			current.setTime(new Date());
			
			if(current.after(lastModify)){
			
				customer.setLastUpdate(new Date());
				customer.setActivateCode(RandomCode.obtainRandomCode());
				logger.info("update CUSTOMER[{}] set new ACTIVATE-CODE[{}]", username, customer.getActivateCode());
				this.dao.update(customer);
			}
			
			logger.info("sending ACTIVATE-CODE[{}] to CUSTOMER[{}]", customer.getActivateCode(), customer.getPhone());
			this.notify.sendMessage(customer.getPhone(), customer.getActivateCode());
		}
	}
	
	@Override
	public Customer resetPassword(String sessionKey, String password) {
		
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.dao.findByHQL(hql, new String[]{"sessionKey"}, new Object[]{sessionKey});
		if(null != customer){

			customer.getCredential().setPassword(password);
			this.dao.update(customer);
			return customer;
		}
		return null;
	}
}
