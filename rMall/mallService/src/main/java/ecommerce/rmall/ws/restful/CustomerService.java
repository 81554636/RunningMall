package ecommerce.rmall.ws.restful;

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Credential;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.service.ICustomerOrderService;
import ecommerce.rmall.ws.ICustomerService;

public class CustomerService implements ICustomerService {

	private ICustomerOrderService orderService;
	private ecommerce.rmall.service.ICustomerService customerService;
	public void setOrderService(ICustomerOrderService service){
		this.orderService = service;
	}
	public void setCustomerService(ecommerce.rmall.service.ICustomerService service){
		this.customerService = service;
	}
	
	@Override
	public Customer register(Customer customer) {
		return this.customerService.create(customer);
	}
	
	@Override
	public Customer update(String userName, Customer customer) {

		if(null == customer.getCredential())
			customer.setCredential( new Credential() );
		customer.getCredential().setUsername(userName);
		return this.customerService.update(customer);
	}

	@Override
	public String activate(String userName, String activateCode) {
		this.customerService.activate(userName, activateCode);
		return "SUCCESS";
	}
	
	@Override
	public String requestPassword(String userName) {
		
		this.customerService.initResetPassword(userName);
		return "SUCCESS";
	}
	
	@Override
	public Customer resetPassword(String userName, String activateCode, String passDigest) {
		
		return this.customerService.resetPassword(userName, activateCode, passDigest);
	}
	
	@Override
	public Customer validate(String userName, String captcha) {
		return this.customerService.validate(userName, captcha);
	}
	
	@Override
	public Customer signIn(String userName, String passDigest) {
		return this.customerService.signIn(userName, passDigest);
	}

	@Override
	public String signOut(String sessionKey) {
		this.customerService.signOut(sessionKey);
		return "SUCCESS";
	}

	@Override
	public Page<Coupon> fetchCoupons(String sessionKey, int pageNumber) {
		
		return this.customerService.coupons(sessionKey, pageNumber);
	}

	@Override
	public Page<Order> orderPagination(String sessionKey, int pageNumber) {

		return this.orderService.ordersPagination(sessionKey, pageNumber);
	}
	
	@Override
	public Order Place(String sessionKey, Order order) {
		
		return this.orderService.Place(sessionKey, 
				order.getDelivery(), 
				order.getDetails(), 
				order.getDescription());
	}
	
	@Override
	public Order Place(String sessionKey, int activityID, Order order) {
		
		return this.orderService.Join(sessionKey, order.getDelivery(), order.getDetails(), activityID);
	}
	
	@Override
	public Order Query(String sessionKey, int orderID) {
		
		return this.orderService.QueryByID(sessionKey, orderID);
	}
	
	@Override
	public String Cancel(String sessionKey, int orderID) {
		
		this.orderService.Cancel(sessionKey, orderID);
		return "SUCCESS";
	}
	
	@Override
	public String Finish(String sessionKey, int orderID) {
		
		this.orderService.Finish(sessionKey, orderID);
		return "SUCCESS";
	}
}
