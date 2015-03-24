package ecommerce.rmall.service.impl;

import java.util.List;

import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.service.IProductService;

public class ProductService implements IProductService {
	
	private ProductDAO dao;
	public void setProductDao(ProductDAO dao){
		this.dao = dao;
	}

	@Override
	public Page<Product> queryWithPage(int pageNumber) {
		return dao.findWithPage(pageNumber);
	}

	@Override
	public List<Product> listAll() {
		return dao.findWithPage(1).getDataList();
	}
}
