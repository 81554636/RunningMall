package ecommerce.rmall.service.restful;

import javax.annotation.Resource;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.service.ICustomerService;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements IOrderService {

	@Resource(name="customerService")
	private ICustomerService service;
	public void setService(ICustomerService service){
		this.service = service;
	}

	@Override
	public Order Place(Order order, long customerID) {
		return this.service.place(order, customerID);
	}

	@Override
	public Order Query(long orderID) {
		Order rtn = this.service.query(orderID);
		return rtn;
	}
}
