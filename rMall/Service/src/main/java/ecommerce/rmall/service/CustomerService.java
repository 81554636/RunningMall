package ecommerce.rmall.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ecommerce.rmall.domain.Customer;

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
}
