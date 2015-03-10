package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.message.MessageSender;
import ecommerce.rmall.service.IOrderService;

public class OrderService implements IOrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
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
	
	private MessageSender msgSender;
	public void setMessageSender(MessageSender msgSender) {
		this.msgSender = msgSender;
	}
	
	@Override
	public Order place(Delivery delivery, List<OrderItem> items) {
		
		String findByPhone = "from Customer where phone=?";
		Customer customer = this.customerDao.findByHQL(findByPhone, new Object[]{delivery.getPhone()});
		if(null == customer){
			
			customer = new Customer();
			customer.setAddress(delivery.getAddress());
			customer.setCreateDate(new Date());
			customer.setName(delivery.getName());
			customer.setPhone(delivery.getPhone());
			
			customer = this.customerDao.save(customer);
		}
		return this.place(delivery, items, customer.getId());
	}
	
	@Override
	public Order place(Delivery delivery, List<OrderItem> items, int customerID) {

		Order order = new Order();
		Customer customer = this.customerDao.findByID(customerID);
		if(customer != null){
			
			order.setCreateDate(new Date());
			order.setLastUpdate(new Date());
			order.setLastUpdateBy("SYSTEM");
			order.setStatus("pending");
			order.setCustomer(customer);
			order.setDelivery(delivery);
			order.setDetails(new java.util.ArrayList<OrderItem>());
			
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
			
			logger.info("persistence ORDER to DataBase");
			this.orderDao.save(order);
			
			logger.info("send ORDER to MessageQueue as PlainText");
			this.msgSender.sendMessage(new com.google.gson.Gson().toJson(order));
		}
		return order;
	}

	@Override
	public Order query(int orderID) {
		return this.orderDao.findByID(orderID);
	}
	
	@Override
	public Page<Order> queryWithPage(int pageNumber) {
		return this.orderDao.findWithPage(pageNumber);
	}
	
	@Override
	public Page<Order> queryByPhoneWithPage(String phone, int pageNumber) {
		String hql = "from Order where customer.phone=?";
		return this.orderDao.findByHQLWithPage(hql, new Object[]{phone}, pageNumber);
	}
	@Override
	public Page<Order> queryPendingWithPage(int pageNumber) {
		String hql = "from Order where status=? order by id desc";
		return this.orderDao.findByHQLWithPage(hql, new Object[]{"pending"}, pageNumber);
	}
	
	@Override
	public Page<Order> queryProcessingWithPage(int pageNumber) {
		String hql = "from Order where status=? order by id desc";
		return this.orderDao.findByHQLWithPage(hql, new Object[]{"processing"}, pageNumber);
	}
	
	@Override
	public void cancel(int orderId) {
		Order order = this.orderDao.findByID(orderId);
		if(null != order){
			order.setStatus("cancel");
			this.orderDao.update(order);
		}
	}
}
