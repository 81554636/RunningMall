package ecommerce.rmall.ws.restful;

import javax.annotation.Resource;

import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.service.IProductService;

public class ProductService implements ecommerce.rmall.ws.IProductService {

	@Resource(name="productService")
	private IProductService productService;
	public void setProductService(IProductService service){
		this.productService = service;
	}
	
	@Override
	public Page<Product> queryWithPage(int pageNumber) {
		return this.productService.queryWithPage(pageNumber);
	}

	@Override
	public Page<Product> queryPagination(String sessionKey, int pageNumber) {
		return this.productService.queryWithPage(sessionKey, pageNumber);
	}
}
