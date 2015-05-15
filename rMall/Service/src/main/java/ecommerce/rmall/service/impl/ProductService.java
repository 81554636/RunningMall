package ecommerce.rmall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Page;
import ecommerce.rmall.domain.Product;
import ecommerce.rmall.service.IProductService;
import ecommerce.rmall.utils.CredentialHelper;
import ecommerce.rmall.utils.IProductHelper;

public class ProductService implements IProductService {
	
	private CustomerDAO customerDao;
	public void setCustomerDao(CustomerDAO dao){ this.customerDao = dao; }
	
	private ProductDAO dao;
	public void setProductDao(ProductDAO dao){ this.dao = dao; }
	
	private IProductHelper helper;
	public void setProductHelper(IProductHelper helper){ this.helper = helper; }
	
	@Override
	public Page<Product> queryWithPage(String sessionKey, int pageNumber) {

		Page<Product> rtn = this.queryWithPage(pageNumber);
		String hql = "from Customer where credential.sessionKey=:sessionKey";
		Customer customer = this.customerDao.findByHQL(
				hql, 
				new String[]{"sessionKey"}, 
				new Object[]{sessionKey});
		if(null != customer && CredentialHelper.validateSession(customer.getCredential())){
		}
		
		return rtn;
	}

	@Override
	public Page<Product> queryWithPage(int pageNumber) {
		return dao.findWithPage(pageNumber);
	}

	@Override
	public List<Product> listAll() {
		return dao.listAll();
	}

	@Override
	public List<Product> listByIDs(int[] ids) {
		
		List<Product> rtn = new ArrayList<Product>();
		Map<Integer, Product> mapProducts = dao.findByIDs(ids);
		for(Map.Entry<Integer, Product> entry : mapProducts.entrySet())
			rtn.add(entry.getValue());
		return rtn;
	}
}
