package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Station;

public interface IStationService {
	
	Station queryByCredential(String username, String password);
	List<Station> listAll();
}
