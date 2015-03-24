package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Station;

public interface IStationService {
	
	Station queryByCity(String city);
	Station queryByCredential(String username, String password);
	List<Station> listAll();
	Station updateChannelInfo(String sessionKey, long channelID, String userID, String osType);
}
