package ecommerce.rmall.service.impl;

import java.util.List;

import ecommerce.rmall.dao.ActivityDAO;
import ecommerce.rmall.domain.Activity;
import ecommerce.rmall.service.IActivityService;

public class ActivityService implements IActivityService {

	private ActivityDAO dao;
	public void setActivityDao(ActivityDAO dao){ this.dao = dao; }
	
	@Override
	public List<Activity> listValid() {
		return this.dao.listValid();
	}
}
