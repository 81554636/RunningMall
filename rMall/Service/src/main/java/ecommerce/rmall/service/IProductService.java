package ecommerce.rmall.service;

import java.util.List;

import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.Page;

public interface IProductService {
	
	Page<Product> queryWithPage(String sessionKey, int pageNumber);
	Page<Product> queryWithPage(int pageNumber);
	List<Product> listAll();
}
