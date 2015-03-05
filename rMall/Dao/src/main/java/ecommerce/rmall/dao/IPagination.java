package ecommerce.rmall.dao;

import ecommerce.rmall.domain.Page;

public interface IPagination<T> {
	Page<T> findWithPage(int pageNumber);
	Page<T> findByHQLWithPage(String hql, Object[] params, int pageNumber);
}
