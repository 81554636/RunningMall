package ecommerce.rmall.ws.restful;

import ecommerce.rmall.domain.Station;
import ecommerce.rmall.service.IStationService;

public class StationService implements ecommerce.rmall.ws.IStationService {

	private IStationService service;
	public void setStationService(IStationService service){
		this.service = service;
	}
	
	@Override
	public Station signIn(String username, String password) {
		Station rtn = this.service.queryByCredential(username, password);
		return rtn;
	}

}
