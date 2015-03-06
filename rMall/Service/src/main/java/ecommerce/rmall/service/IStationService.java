package ecommerce.rmall.service;

import ecommerce.rmall.domain.Station;

public interface IStationService {
	
	Station queryByCredential(String username, String password);
}
