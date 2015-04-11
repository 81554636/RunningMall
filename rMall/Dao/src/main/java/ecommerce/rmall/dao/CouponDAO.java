package ecommerce.rmall.dao;

import java.util.List;

import ecommerce.rmall.domain.Coupon;
import ecommerce.rmall.domain.Page;

public class CouponDAO extends DaoSupport{
	
	public Coupon findByID(int identity){
		
		return super.get(Coupon.class, identity);
	}
	
	public Page<Coupon> findByHQLWithPage(String hql, String[] params, Object[] values, int pageNumber) {
		
		Page<Coupon> page = new Page<Coupon>();
		page.setCurrentPage(pageNumber);
		List<Coupon> rtn = super.queryForList(hql, params, values, page);
		page.setDataList(rtn);
		return page;
	}
}
