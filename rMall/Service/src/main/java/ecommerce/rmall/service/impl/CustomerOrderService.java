package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.OrderStatus;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.ShipmentStatus;
import ecommerce.rmall.message.MessageSender;
import ecommerce.rmall.service.ICustomerOrderService;
import ecommerce.rmall.utils.CredentialHelper;

public class CustomerOrderService implements ICustomerOrderService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderService.class);
	
	// +++++ DAOs +++++
	private OrderDAO orderDao;
	private CustomerDAO customerDao;
	private ProductDAO productDao;
	private ShipmentDAO shipmentDao;
	public void setOrderDao(OrderDAO dao) {
		this.orderDao = dao;
	}
	public void setCustomerDao(CustomerDAO dao) {
		this.customerDao = dao;
	}
	public void setProductDao(ProductDAO dao){
		this.productDao = dao;
	}
	public void setShipmentDao(ShipmentDAO dao){
		this.shipmentDao = dao;
	}
	
	// +++++ MessageSender +++++
	private MessageSender msgSender;
	public void setMessageSender(MessageSender msgSender) {
		this.msgSender = msgSender;
	}

	@Override
	public Order Join(String sessionKey, Delivery delivery, List<OrderItem> items, int activityID) {

		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
			
			Order newOrder = new Order();
			newOrder.setCreateDate(new Date());
			newOrder.setLastUpdate(new Date());
			newOrder.setLastUpdateBy("SYSTEM");
			newOrder.setStatus(OrderStatus.PENDING);
			newOrder.setCustomer(customer);
			newOrder.setDelivery(delivery);
			newOrder.setDetails(new java.util.ArrayList<OrderItem>());
			
			int[] ids = new int[items.size()];
			int index = 0;
			for(OrderItem item:items)
				ids[index++] = item.getProduct().getId();
			Map<Integer, Product> products = this.productDao.findByIDs(ids);
			
			for(OrderItem item : items){
				item.setProduct(products.get(item.getProduct().getId()));
				newOrder.getDetails().add(item);
			}
			
			logger.info("persistence ORDER to DataBase");
			this.orderDao.save(newOrder);
			
			logger.info("send ORDER to MessageQueue as PlainText");
			this.msgSender.sendMessage(new com.google.gson.Gson().toJson(newOrder));
			
			return newOrder;
		}
		
		return null;
	}
	
	@Override
	public Order Place(int customerID, Delivery delivery, List<OrderItem> items) {
		
		String hql = "from Customer where id=:customerID";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"customerID"}, 
				new Object[]{customerID});
		
		if(null != customer){
			
			Order newOrder = new Order();
			newOrder.setCreateDate(new Date());
			newOrder.setLastUpdate(new Date());
			newOrder.setLastUpdateBy("ADVISOR");
			newOrder.setStatus(OrderStatus.PENDING);
			newOrder.setCustomer(customer);
			newOrder.setDelivery(delivery);
			newOrder.setDetails(new java.util.ArrayList<OrderItem>());
			
			int[] ids = new int[items.size()];
			int index = 0;
			for(OrderItem item:items)
				ids[index++] = item.getProduct().getId();
			Map<Integer, Product> products = this.productDao.findByIDs(ids);
			
			index = 0;
			for(OrderItem item : items){
				Product product = products.get(item.getProduct().getId());
				item.setProduct(product);
				newOrder.getDetails().add(item);				
			}
			
			logger.info("persistence ORDER to DataBase");
			this.orderDao.save(newOrder);
			
			logger.info("send ORDER to MessageQueue as PlainText");
			this.msgSender.sendMessage(new com.google.gson.Gson().toJson(newOrder));
			
			return newOrder;
		}
		return null;
	}
	
	@Override
	public Order Place(String sessionKey, Delivery delivery, List<OrderItem> items, String description) {
		
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
			
			Order newOrder = new Order();
			newOrder.setCreateDate(new Date());
			newOrder.setLastUpdate(new Date());
			newOrder.setLastUpdateBy("SYSTEM");
			newOrder.setStatus(OrderStatus.PENDING);
			newOrder.setCustomer(customer);
			newOrder.setDelivery(delivery);
			newOrder.setDetails(new java.util.ArrayList<OrderItem>());
			newOrder.setDescription(description);
			
			int[] ids = new int[items.size()];
			int index = 0;
			for(OrderItem item:items)
				ids[index++] = item.getProduct().getId();
			Map<Integer, Product> products = this.productDao.findByIDs(ids);
			
			index = 0;
			for(OrderItem item : items){
				Product product = products.get(item.getProduct().getId());
				item.setProduct(product);
				newOrder.getDetails().add(item);				
			}
			
			logger.info("persistence ORDER to DataBase");
			this.orderDao.save(newOrder);
			
			logger.info("send ORDER to MessageQueue as PlainText");
			this.msgSender.sendMessage(new com.google.gson.Gson().toJson(newOrder));
			
			return newOrder;
		}
		return null;
	}

	//TODO:这里有个BUG, 必须校验order.customer是否就是sessionKey代表的customer
	@Override
	public Order QueryByID(String sessionKey, int orderID) {

		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		if(null != customer && CredentialHelper.validateSession(customer.getCredential()))
			return this.orderDao.findByID(orderID);
		
		return null;
	}

	@Override
	public Page<Order> ordersPagination(String sessionKey, int pageNumber) {

		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
			
			String hqlBySessionKey = "from Order where customer.credential.sessionKey=:sessionKey order by id desc";
			return this.orderDao.findByHQLWithPage(
					hqlBySessionKey, 
					new String[]{"sessionKey"}, 
					new Object[]{sessionKey}, 
					pageNumber);
		}
		return null;
	}

	//TODO:这里有个BUG, 必须校验order.customer是否就是sessionKey代表的customer
	@Override
	public void Cancel(String sessionKey, int orderID) {
		
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
			
			Order order = this.orderDao.findByID(orderID);
			if(null != order && OrderStatus.PENDING==order.getStatus()){
				order.setStatus(OrderStatus.CANCEL);
				this.orderDao.update(order);
			}
		}
	}

	//TODO:这里有个BUG, 必须校验order.customer是否就是sessionKey代表的customer
	@Override
	public void Finish(String sessionKey, int orderID) {
		
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
			
			Order order = this.orderDao.findByID(orderID);
			if( null == order)
				return;
			
			if(OrderStatus.PROCESSING == order.getStatus() && null != order.getShipment()){
				order.getShipment().setStatus(ShipmentStatus.FINISH);
				this.shipmentDao.update(order.getShipment());
			}
			order.setStatus(OrderStatus.FINISH);
			this.orderDao.update(order);
		}
	}
	
}
