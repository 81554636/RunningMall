package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Shipment;

public class ShipmentDAO extends DaoSupport {
	
	public void save(Shipment order){
		super.save(order);
	}
	
	public Shipment findByID(int identity){
		return super.get(Shipment.class, identity);
	}
}
