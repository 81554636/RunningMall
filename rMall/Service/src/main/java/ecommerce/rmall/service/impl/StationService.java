package ecommerce.rmall.service.impl;

import java.util.List;
import java.util.UUID;

import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IStationService;

public class StationService implements IStationService {

	private StationDAO stationDao;
	public void setStationDao(StationDAO stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public Station queryByCredential(String username, String password) {
		
		String hql = "from Station where credential.username=?";
		Station station = this.stationDao.findByHQL(hql, new Object[]{username});
		if(null != station){
			if(station.getCredential().getPassword().equals(password)){
				String sessionKey = UUID.randomUUID().toString();
				station.getCredential().setSessionKey(sessionKey);
				this.stationDao.update(station);
			}
		}
		return station;
	}

	@Override
	public List<Station> listAll() {
		return this.stationDao.listByHQL("from Station", new Object[]{});
	}
}
