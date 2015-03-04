package ecommerce.rmall.service;

import ecommerce.rmall.domain.Product;
import ecommerce.rmall.domain.Page;

public interface IProductService {
	
	Page<Product> queryWithPage(int pageNumber);
}
