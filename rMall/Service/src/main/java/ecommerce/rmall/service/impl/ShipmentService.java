package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IShipmentService;

public class ShipmentService implements IShipmentService {

	private StationDAO stationDao;
	private ShipmentDAO shipDao;
	
	public void setStationDao(StationDAO stationDao) {
		this.stationDao = stationDao;
	}
	public void setShipDao(ShipmentDAO shipDao) {
		this.shipDao = shipDao;
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
			
			shipDao.save(shipment);
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
		String hql = "from Shipment where sessionKey=?";
		return this.shipDao.findByHQLWithPage(hql, new Object[]{sessionKey}, pageNumber);
	}
}