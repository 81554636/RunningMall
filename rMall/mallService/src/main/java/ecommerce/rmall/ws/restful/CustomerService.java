package ecommerce.rmall.ws.restful;

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderStatus;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.service.ICustomerOrderService;
import ecommerce.rmall.ws.ICustomerService;
import ecommerce.rmall.ws.Status;

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
	public Customer update(String sessionKey, Customer customer) {

		return this.customerService.update(sessionKey, customer);
	}

	@Override
	public String activate(String sessionKey) {
		
		this.customerService.activate(sessionKey);
		return "SUCCESS";
	}
	
	@Override
	public String requestPassword(String userName) {
		
		this.customerService.initResetPassword(userName);
		return "SUCCESS";
	}
	
	@Override
	public Customer resetPassword(String sessionKey, String passDigest) {
		
		return this.customerService.resetPassword(sessionKey, passDigest);
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
	@Override
	public Page<Order> orderPaginationByStatus(String sessionKey, Status status, int pageNumber) {
		
		ecommerce.rmall.domain.OrderStatus query = OrderStatus.PENDING;
		if(Status.CANCEL.equals(status))
			query = OrderStatus.CANCEL;
		else if(Status.FINISH.equals(status))
			query = OrderStatus.FINISH;
		
		return this.orderService.ordersPaginationByStatus(sessionKey, query, pageNumber);
	}
}
