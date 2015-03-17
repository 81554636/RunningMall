package ecommerce.rmall.ws.restful;

import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.Page;
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
	public Page<Shipment> queryByStation(String sessionKey, int pageNumber) {
		return this.shipmentServ.queryBySessionWithPage(sessionKey, pageNumber);
	}

	@Override
	public String finish(int shipmentID, String accessCode) {
		
		try {
			this.shipmentServ.finish(shipmentID, accessCode);
			return "SUCCESS";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
