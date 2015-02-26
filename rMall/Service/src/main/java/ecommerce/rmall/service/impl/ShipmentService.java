package ecommerce.rmall.service.impl;

import java.util.Date;
import java.util.List;

import ecommerce.rmall.dao.ShipmentDAO;
import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Order;
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
			shipment.setDetails(order.getDetails());
			shipment.setStation(station);
			shipment.setStatus("INIT");
			
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
}
