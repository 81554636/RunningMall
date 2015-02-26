package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Order;

public interface IShipmentService {
	Shipment dispatch(Order order, int stationID);
	Shipment queryByID(int shipmentID);
	List<Shipment> queryByStation(int stationID);
}