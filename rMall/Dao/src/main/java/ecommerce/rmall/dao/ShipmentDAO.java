package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Shipment;

public class ShipmentDAO extends DaoSupport {
	
	public void save(Shipment shipment){
		super.save(shipment);
	}
	
	public Shipment findByID(int identity){
		return super.get(Shipment.class, identity);
	}
}
