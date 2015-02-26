package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements IOrderService {

	private CustomerDAO customerDao;
	private OrderDAO orderDao;
	private ProductDAO productDao;	
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Order place(Delivery delivery, Set<OrderItem> items, int customerID) {

		Order order = new Order();
		Customer customer = this.customerDao.findByID(customerID);
		if(customer != null){
			
			order.setCreateDate(new Date());
			order.setLastUpdate(new Date());
			order.setLastUpdateBy("SYSTEM");
			order.setStatus("INIT");
			order.setCustomer(customer);
			order.setDelivery(delivery);
			order.setDetails(new java.util.HashSet<OrderItem>());
			
			int[] ids = new int[items.size()];
			int index = 0;
			for(OrderItem item:items)
				ids[index++] = item.getProduct().getId();
			List<Product> products = this.productDao.findByIDs(ids);
			index = 0;
			for(OrderItem item : items){
				item.setProduct(products.get(index++));
				order.getDetails().add(item);
			}
			this.orderDao.save(order);
		}
		return order;
	}

	@Override
	public Order query(int orderID) {
		return this.orderDao.findByID(orderID);
	}
}
