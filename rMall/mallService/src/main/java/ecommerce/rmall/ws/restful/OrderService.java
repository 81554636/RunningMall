package ecommerce.rmall.ws.restful;

import javax.annotation.Resource;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements ecommerce.rmall.ws.IOrderService {
	
	@Resource(name="orderService")
	private IOrderService orderService;
	public void setOrderService(IOrderService service){
		this.orderService = service;
	}

	@Override
	public Order Place(Order order, int customerID) {
		return this.orderService.place(order, customerID);
	}

	@Override
	public Order Query(int orderID) {
		Order rtn = this.orderService.query(orderID);
		return rtn;
	}
}
