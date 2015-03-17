package ecommerce.rmall.service;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Order;

public interface IShipmentService {
	Shipment dispatch(Order order, int stationID);
	
	Shipment queryByID(int shipmentID);
	Page<Shipment> queryWithPage(int pageNumber);
	Page<Shipment> queryBySessionWithPage(String sessionKey, int pageNumber);
	Page<Shipment> queryByNameWithPage(String name, int pageNumber);
	
	void finish(int shipmentID);
	void finish(int shipmentID, String accessCode) throws Exception;
}
