package ecommerce.rmall.ws.util;

import ecommerce.rmall.domain.Product;
import ecommerce.rmall.ws.ProductDTO;

public class ProductConvert {
	static public ProductDTO convert(Product prod){
		ProductDTO rtn = new ProductDTO();
		rtn.setDisplayName(prod.getDisplayName());
		rtn.setIdentity(prod.getId());
		rtn.setPrice(prod.getPrice());
		return rtn;
	}
}
