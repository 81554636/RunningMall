package ecommerce.rmall.dao;

import java.util.List;

import ecommerce.rmall.domain.Station;

public class StationDAO extends DaoSupport {
	
	public void update(Station station){
		super.update(station);
	}
	
	public Station findByID(int identity){
		return super.get(Station.class, identity);
	}
	
	public Station findByHQL(String hql, String[] params, Object[] values){
		
		List<Station> result = this.listByHQL(hql, params, values);
		if(null != result && result.size() > 0)
			return result.get(0);
		else
			return null;
	}
	
	public List<Station> listByHQL(String hql, String[] params, Object[] values){
		return super.queryByHql(hql, params, values);
	}
}
