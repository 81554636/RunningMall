package ecommerce.rmall.dao;

import java.util.List;

import org.hibernate.Query;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Shipment;

public class ShipmentDAO extends DaoSupport implements IPagination<Shipment>{
	
	public void save(Shipment shipment){
		super.save(shipment);
	}
	
	public void update(Shipment shipment){
		super.update(shipment);
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

	@Override
	public Page<Shipment> findWithPage(int pageNumber) {
		
		Page<Shipment> page = new Page<Shipment>();
		page.setCurrentPage(pageNumber);
		List<Shipment> rtn = super.queryForList("from Shipment", new Object[]{}, page);
		page.setDataList(rtn);
		return page;
	}

	@Override
	public Page<Shipment> findByHQLWithPage(String hql, Object[] params, int pageNumber) {
		
		Page<Shipment> page = new Page<Shipment>();
		page.setCurrentPage(pageNumber);
		List<Shipment> rtn = super.queryForList(hql, params, page);
		page.setDataList(rtn);
		return page;
	}
}
