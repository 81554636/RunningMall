package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.CountByDate;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Delivery;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.OrderStatus;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.ShipmentStatus;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.message.MessageSender;
import ecommerce.rmall.service.IOrderService;
import ecommerce.rmall.service.IShipmentService;

public class OrderService implements IOrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	private CustomerDAO customerDao;
	private OrderDAO orderDao;
	private ProductDAO productDao;
	private ShipmentDAO shipmentDao;
	private StationDAO stationDao;
	public void setStationDao(StationDAO dao){
		this.stationDao = dao;
	}
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	public void setShipmentDao(ShipmentDAO shipmentDao){
		this.shipmentDao = shipmentDao;
	}
	
	private IShipmentService shipmentService;
	public void setShipmentService(IShipmentService service){
		this.shipmentService = service;
	}
	
	private MessageSender msgSender;
	public void setMessageSender(MessageSender msgSender) {
		this.msgSender = msgSender;
	}
	
	@Override
	public Order place(Delivery delivery, List<OrderItem> items) {
		
		String findByPhone = "from Customer where phone=:phone";
		Customer customer = this.customerDao.findByHQL(findByPhone, new String[]{"phone"}, new Object[]{delivery.getPhone()});
		if(null == customer){
			
			customer = new Customer();
			customer.setAddress(delivery.getAddress());
			customer.setCreateDate(new Date());
			customer.setName(delivery.getName());
			customer.setPhone(delivery.getPhone());
			
			logger.info("persistence CUSTOMER to DataBase");
			customer = this.customerDao.save(customer);
		}
		return this.place(delivery, items, customer.getId());
	}
	
	@Override
	public Order placeAndDistribute(Delivery delivery, List<OrderItem> items) {
		
		String findByPhone = "from Customer where phone=:phone";
		Customer customer = this.customerDao.findByHQL(findByPhone, new String[]{"phone"}, new Object[]{delivery.getPhone()});
		if(null == customer){//创建客户
			
			customer = new Customer();
			customer.setAddress(delivery.getAddress());
			customer.setCreateDate(new Date());
			customer.setName(delivery.getName());
			customer.setPhone(delivery.getPhone());
			
			logger.info("persistence CUSTOMER to DataBase");
			customer = this.customerDao.save(customer);
		}
		
		//创建订单
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setLastUpdate(new Date());
		order.setLastUpdateBy("SYSTEM");
		order.setStatus(OrderStatus.PENDING);
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
		
		String hql = "from Station where city=:city";
		Station station = this.stationDao.findByHQL(hql, new String[]{"city"}, new Object[]{delivery.getCity()});
		if(null != station){//找到相应小站
			Shipment ship = this.shipmentService.dispatch(order, station.getId());
		} else {//没找到小站
			logger.info("send ORDER to MessageQueue as PlainText");
			this.msgSender.sendMessage(new com.google.gson.Gson().toJson(order));
		}
		return order;
	}
	
	@Override
	public Order place(Delivery delivery, List<OrderItem> items, int customerID) {

		Order order = new Order();
		Customer customer = this.customerDao.findByID(customerID);
		if(customer != null){
			
			order.setCreateDate(new Date());
			order.setLastUpdate(new Date());
			order.setLastUpdateBy("SYSTEM");
			order.setStatus(OrderStatus.PENDING);
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
		String hql = "from Order where customer.phone=:phone order by id desc";
		return this.orderDao.findByHQLWithPage(
				hql, 
				new String[]{"phone"}, 
				new Object[]{phone}, 
				pageNumber);
	}
	@Override
	public Page<Order> queryPendingWithPage(int pageNumber) {
		String hql = "from Order where status=:status order by id desc";
		return this.orderDao.findByHQLWithPage(
				hql, 
				new String[]{"status"}, 
				new Object[]{OrderStatus.PENDING}, 
				pageNumber);
	}
	
	@Override
	public Page<Order> queryProcessingWithPage(int pageNumber) {
		String hql = "from Order where status=:status order by id desc";
		return this.orderDao.findByHQLWithPage(
				hql, 
				new String[]{"status"}, 
				new Object[]{OrderStatus.PROCESSING}, 
				pageNumber);
	}
	
	@Override
	public void cancel(int orderId) {
		
		Order order = this.orderDao.findByID(orderId);
		if(null != order && OrderStatus.PENDING==order.getStatus()){
			order.setStatus(OrderStatus.CANCEL);
			this.orderDao.update(order);
		}
	}
	
	@Override
	public void finish(int orderId) {
		
		Order order = this.orderDao.findByID(orderId);
		if(null != order){

			if(OrderStatus.PROCESSING == order.getStatus() && null != order.getShipment()){
				order.getShipment().setStatus(ShipmentStatus.FINISH);
				this.shipmentDao.update(order.getShipment());
			}
			order.setStatus(OrderStatus.FINISH);
			this.orderDao.update(order);
		}
	}
	@Override
	public List<CountByDate> countByDate() {
		return this.orderDao.count();
	}
	@Override
	public void update(Order order) {
		order.setLastUpdate(new Date());
		this.orderDao.update(order);
	}
}
