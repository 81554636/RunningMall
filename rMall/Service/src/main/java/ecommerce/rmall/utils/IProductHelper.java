package ecommerce.rmall.utils;

import java.util.List;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Product;

public interface IProductHelper {

	/***
	 * 获取用户的活动产品
	 * 例:注册送好礼
	 * @param customer
	 * @return
	 */
	List<Product> queryActivities(Customer customer);
	
	/***
	 * 移除客户活动产品
	 * @param addition
	 * @param customer
	 */
	void removeActivity(Product addition, Customer customer);
}
