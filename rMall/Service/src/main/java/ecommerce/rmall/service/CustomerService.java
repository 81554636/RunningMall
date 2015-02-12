package ecommerce.rmall.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ecommerce.rmall.domain.Customer;
import ecommerce.rmall.domain.Order;
import ecommerce.rmall.domain.OrderItem;

public class CustomerService implements ICustomerService {

	@PersistenceContext()
    private EntityManager entityManager;

	@Override
	public void saveCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return this.entityManager.createQuery("select c from Customer c").getResultList();
	}
	
	@Override
	public Order place(Order order, long customerID){
		
		Customer customer = this.entityManager.find(Customer.class, customerID);
		if(customer != null){
			order.setCreateDate(new Date());
			order.setLastUpdate(new Date());
			order.setLastUpdateBy("SYSTEM");
			order.setStatus("INIT");
			order.setCustomer(customer);
			this.entityManager.persist(order);
		}
		return order;
	}
	
	@Override
	public Order query(long orderID){
		return this.entityManager.find(Order.class, orderID);
	}
}
