package ecommerce.rmall.ws.restful;

import java.util.List;

import ecommerce.rmall.domain.Channel;
import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IStationService;

public class StationService implements ecommerce.rmall.ws.IStationService {

	private IStationService service;
	public void setStationService(IStationService service){
		this.service = service;
	}
	
	@Override
	public List<Station> listAll() {
		return this.service.listAll();
	}
	
	@Override
	public Station signIn(String username, String password) {
		Station rtn = this.service.queryByCredential(username, password);
		return rtn;
	}

	@Override
	public String updateChannel(String sessionKey, long channelID, String userID, String osType) {
		Station rtn = this.service.updateChannelInfo(sessionKey, channelID, userID, osType);
		return "SUCCESS";
	}

	@Override
	public String updateChannel(String sessionKey, Channel channel) {
		Station rtn = this.service.updateChannelInfo(sessionKey, 
				channel.getChannelID(), channel.getUserID(), channel.getOsType());
		return "SUCCESS";
	}
}
