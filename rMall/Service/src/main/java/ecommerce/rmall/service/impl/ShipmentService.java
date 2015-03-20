package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.OrderDAO;
import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.OrderStatus;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.ShipmentStatus;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.message.MessageSender;
import ecommerce.rmall.service.IShipmentService;
import ecommerce.rmall.utils.RandomCode;

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
			//shipment.setStatus("INIT");
			shipment.setStatus(ShipmentStatus.INIT);
			shipment.setDetails(new HashSet<OrderItem>());
			for(OrderItem item : order.getDetails()){
				OrderItem newItem = new OrderItem();
				newItem.setId(item.getId());
				shipment.getDetails().add(newItem);
			}
			
			logger.info("persistence ORDER & SHIPMENT to Database");
			this.shipDao.save(shipment);
			
			//order.setStatus("processing");
			order.setStatus(OrderStatus.PROCESSING);
			order.setShipment(shipment);
			order.setAccessCode(RandomCode.obtainRandomCode());
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
	public Page<Shipment> queryWithPage(int pageNumber){
		return this.shipDao.findWithPage(pageNumber);
	}
	
	@Override
	public Page<Shipment> queryBySessionWithPage(String sessionKey, int pageNumber) {
		
		String hql = "from Shipment where station.credential.sessionKey=:sessionKey order by id desc";
		return this.shipDao.findByHQLWithPage(hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey}, 
				pageNumber);
	}
	
	@Override
	public Page<Shipment> queryByNameWithPage(String name, int pageNumber) {
		
		String hql = "from Shipment where station.name=:station_name order by id desc";
		return this.shipDao.findByHQLWithPage(hql, 
				new String[]{"station"}, 
				new Object[]{name}, 
				pageNumber);
	}
	
	@Override
	public void finish(int shipmentID) {
		
		Shipment shipment = this.shipDao.findByID(shipmentID);
		if(null != shipment){
			Order order = this.orderDao.findByHQL("from Order where shipment=shipment", new String[]{"shipment"}, new Object[]{shipment});
			//shipment.setStatus("finish");
			shipment.setStatus(ShipmentStatus.FINISH);
			if( null != order ){
				//order.setStatus("finish");
				order.setStatus(OrderStatus.FINISH);
				this.orderDao.update(order);
			}
			this.shipDao.update(shipment);
		}
	}

	@Override
	public void finish(int shipmentID, String accessCode) throws Exception {

		Shipment shipment = this.shipDao.findByID(shipmentID);
		if(null != shipment){
			
			Order order = this.orderDao.findByHQL("from Order where shipment=:shipment", new String[]{"shipment"}, new Object[]{shipment});
			if( null != order && order.getAccessCode().equals(accessCode)){
				
				//shipment.setStatus("finish");
				shipment.setStatus(ShipmentStatus.FINISH);
				this.shipDao.update(shipment);
				
				//order.setStatus("finish");
				order.setStatus(OrderStatus.FINISH);
				this.orderDao.update(order);
			} else {
				throw new Exception("Invalid Access Code");
			}
		}	
	}
}