package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.message.MessageSender;
import ecommerce.rmall.service.IShipmentService;

public class ShipmentService implements IShipmentService {

	private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);
	
	private StationDAO stationDao;
	private ShipmentDAO shipDao;
	private OrderDAO orderDao;
	
	public void setStationDao(StationDAO stationDao) {
		this.stationDao = stationDao;
	}
	public void setShipDao(ShipmentDAO shipDao) {
		this.shipDao = shipDao;
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	
	private MessageSender messageSender;
	public void setMessageSender(MessageSender sender){
		this.messageSender = sender;
	}
	
	@Override
	public Shipment dispatch(Order order, int stationID) {
		
		Station station = this.stationDao.findByID(stationID);
		Shipment shipment = new Shipment();
		if(station != null){
			
			shipment.setCreateDate(new Date());
			shipment.setLastUpdate(new Date());
			shipment.setDelivery(order.getDelivery());
			shipment.setStation(station);
			shipment.setStatus("INIT");
			shipment.setDetails(new HashSet<OrderItem>());
			for(OrderItem item : order.getDetails()){
				OrderItem newItem = new OrderItem();
				newItem.setId(item.getId());
				shipment.getDetails().add(newItem);
			}
			
			logger.info("persistence ORDER & SHIPMENT to Database");
			this.shipDao.save(shipment);
			
			order.setStatus("processing");
			order.setShipment(shipment);
			this.orderDao.update(order);
			
			logger.info("send SHIPMENT to MessageQueue as PlainText");
			this.messageSender.sendMessage(new com.google.gson.Gson().toJson(shipment));
		}
		return shipment;
	}
	
	@Override
	public Shipment queryByID(int shipmentID) {
		return this.shipDao.findByID(shipmentID);
	}
	
	@Override
	public List<Shipment> queryByStation(int stationID) {
		return this.shipDao.findByStationID(stationID);
	}
	
	@Override
	public Page<Shipment> queryBySession(String sessionKey, int pageNumber) {
		String hql = "from Shipment where station.credential.sessionKey=?";
		return this.shipDao.findByHQLWithPage(hql, new Object[]{sessionKey}, pageNumber);
	}
}