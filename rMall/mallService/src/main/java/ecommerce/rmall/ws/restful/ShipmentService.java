package ecommerce.rmall.ws.restful;

import java.util.List;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Shipment;
import ecommerce.rmall.ws.IShipmentService;

public class ShipmentService implements IShipmentService {

	private ecommerce.rmall.service.IShipmentService shipmentServ;
	public void setShipmentService(ecommerce.rmall.service.IShipmentService service){
		this.shipmentServ = service;
	}
	
	private ecommerce.rmall.service.IOrderService orderServ;
	public void setOrderService(ecommerce.rmall.service.IOrderService service){
		this.orderServ = service;
	}
	
	@Override
	public Shipment dispatch(int orderID, int stationID) {
		
		Order order = this.orderServ.query(orderID);
		Shipment rtn = new Shipment();
		if(null != order)
			rtn = this.shipmentServ.dispatch(order, stationID);
		return rtn;
	}

	@Override
	public Shipment queryByID(int shipmentID) {
		return this.shipmentServ.queryByID(shipmentID);
	}

	@Override
	public List<Shipment> queryByStation(int stationID) {
		return this.shipmentServ.queryByStation(stationID);
	}
}