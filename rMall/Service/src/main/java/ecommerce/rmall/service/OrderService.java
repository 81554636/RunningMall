package ecommerce.rmall.service;

import java.util.Date;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;

public class OrderService implements IOrderService {

	private CustomerDAO customerDao;
	private OrderDAO orderDao;
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	public OrderDAO getOrderDao() {
		return orderDao;
	}

	@Override
	public Order place(Order order, int customerID) {

		Customer customer = this.customerDao.findByID(customerID);
		if(customer != null){
			
			order.setCreateDate(new Date());
			order.setLastUpdate(new Date());
			order.setLastUpdateBy("SYSTEM");
			order.setStatus("INIT");
			order.setCustomer(customer);
			this.orderDao.save(order);
		}
		return order;
	}

	@Override
	public Order query(int orderID) {
		return this.orderDao.findByID(orderID);
	}
}
