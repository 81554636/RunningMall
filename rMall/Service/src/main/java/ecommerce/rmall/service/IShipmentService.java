package ecommerce.rmall.service;

import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.domain.Order;

public interface IShipmentService {
	Shipment dispatch(Order order, int stationID);
}
