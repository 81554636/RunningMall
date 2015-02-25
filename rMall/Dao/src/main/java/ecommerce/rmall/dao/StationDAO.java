package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Station;

public class StationDAO extends DaoSupport {
	
	public Station findByID(int identity){
		return super.get(Station.class, identity);
	}
}
