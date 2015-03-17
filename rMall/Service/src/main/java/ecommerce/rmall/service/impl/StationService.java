package ecommerce.rmall.service.impl;

import java.util.List;
import java.util.UUID;

import ecommerce.rmall.dao.StationDAO;
import ecommerce.rmall.domain.Channel;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IStationService;

public class StationService implements IStationService {

	private StationDAO stationDao;
	public void setStationDao(StationDAO stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public Station queryByCredential(String username, String password) {
		
		String hql = "from Station where credential.username=:username";
		Station station = this.stationDao.findByHQL(hql, new String[]{"username"}, new Object[]{username});
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
		return this.stationDao.listByHQL("from Station", new String[]{}, new Object[]{});
	}

	@Override
	public Station updateChannelInfo(String sessionKey, long channelID, String userID, String osType){
		
		String hql = "from Station where credential.sessionKey=:sessionKey";
		Station station = this.stationDao.findByHQL(hql, new String[]{"sessionKey"}, new Object[]{sessionKey});
		if( null != station ){
			Channel channel = station.getChannel() == null ? new Channel() : station.getChannel();
			channel.setChannelID(channelID);
			channel.setUserID(userID);
			channel.setOsType(osType);
			station.setChannel(channel);
			this.stationDao.update(station);
			return station;
		} else{
			return null;
		}
	}
}
