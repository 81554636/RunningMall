package ecommerce.rmall.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecommerce.rmall.dao.CustomerDAO;
import ecommerce.rmall.dao.ProductDAO;
import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Product;

public class ProductHelper implements IProductHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductHelper.class);
	
	private ProductDAO dao;
	public void setProductDao(ProductDAO dao){ this.dao = dao; }
	
	private CustomerDAO customerDao;
	public void setCustomerDao(CustomerDAO dao) { this.customerDao = dao; }
	
	static final int ID_ACTIVITY = 1;
	
	@Override
	public List<Product> queryActivities(Customer customer){
		
		logger.debug("queryActivities CUSTOMER={ id:{}, isJoinActivity:{} }", customer.getId(), customer.isJoinActivity());
		List<Product> rtn = new ArrayList<Product>();
		if(customer.isJoinActivity()){
			Product activity = this.dao.findByID(ID_ACTIVITY);
			rtn.add(activity);
			logger.debug("queryActivities ACTIVITY={ id:{}, displayName:{} }", activity.getId(), activity.getDisplayName());
		}
		return rtn;
	}

	@Override
	public void removeActivity(Product addition, Customer customer) {

		if(addition.getId() == ID_ACTIVITY && customer.isJoinActivity()){
			customer.setJoinActivity(false);
			this.customerDao.update(customer);
			logger.debug("removeActivity CUSTOMER={ id:{}, isJoinActivity:{} }, ACTIVITY={ id:{}, displayName:{} }", 
					customer.getId(), customer.isJoinActivity(),
					addition.getId(), addition.getDisplayName());
		}
	}

}
