package ecommerce.rmall.ws.restful;

import javax.annotation.Resource;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements ecommerce.rmall.ws.IOrderService {
	
	@Resource(name="orderService")
	private IOrderService orderService;
	public void setOrderService(IOrderService service){
		this.orderService = service;
	}

	@Override
	public Order Place(Order order, int customerID){
		Order newOrder = this.orderService.place(order.getDelivery(), order.getDetails(), customerID);
		return newOrder;
	}

	@Override
	public Order Query(int orderID) {
		Order rtn = this.orderService.query(orderID);
		return rtn;
	}

	@Override
	public Page<Order> Query(String phone, int pageNumber) {
		Page<Order> rtn = this.orderService.queryByPhoneWithPage(phone, pageNumber);
		return rtn;
	}
}
