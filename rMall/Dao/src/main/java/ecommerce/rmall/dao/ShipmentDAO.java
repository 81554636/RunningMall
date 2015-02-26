package ecommerce.rmall.dao;

import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.Shipment;

public class ShipmentDAO extends DaoSupport {
	
	public void save(Shipment shipment){
		super.save(shipment);
	}
	
	public Shipment findByID(int identity){
		return super.get(Shipment.class, identity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Shipment> findByStationID(int identity){
		String hql = "from Shipment where station.id=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, identity);
		return query.list();
	}
}
