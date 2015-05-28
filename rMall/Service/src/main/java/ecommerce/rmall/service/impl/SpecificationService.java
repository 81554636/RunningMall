package ecommerce.rmall.service.impl;

import java.util.List;

import ecommerce.rmall.dao.SpecificationDAO;
import ecommerce.rmall.domain.Specification;
import ecommerce.rmall.service.ISpecificationService;

public class SpecificationService implements ISpecificationService {

	private SpecificationDAO dao;
	public void setSpecificationDao(SpecificationDAO dao){ this.dao = dao; }
	
	@Override
	public List<Specification> listAll() {
		return this.dao.listAll();
	}
}
