package com.pk.customer.publisher.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pk.customer.publisher.model.Address;
import com.pk.customer.publisher.model.Customer;

@Repository("customerPublisherDao")
public class CustomerPublisherDaoImpl implements CustomerPublisherDao {

	private SessionFactory hibernateFactory;

	private Session session;

	@Autowired
	private CustomerReposistry customerReposistry;

	@Autowired
	private AddressRepostory addressRepostory;

	@Autowired
	public CustomerPublisherDaoImpl(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
		}
		this.hibernateFactory = factory.unwrap(SessionFactory.class);
		session = hibernateFactory.openSession();
	}

	@Override
	public Integer saveCustomer(Customer customer) {
		Integer custId = (Integer) session.save(customer);
		return custId;
	}

	@Override
	public void saveAddress(Address address) {
		session.save(address);
	}

	@Override
	public Customer getCustomerByCustId(Integer custId) {
		return customerReposistry.findById(custId).get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerReposistry.findAll();
	}

	@Override
	public void updateCustomer(Customer updateCustomer) {
		customerReposistry.save(updateCustomer);
	}

	@Override
	public Address getCustomerAddressByCustId(Integer custId) {
		@SuppressWarnings("deprecation")
		Address address = (Address) session.createCriteria(Address.class).add(Restrictions.eq("custId", custId))
				.uniqueResult();
		return address;
	}

	@Override
	public void deleteCustomer(Customer customer, Address address) {
		customerReposistry.delete(customer);
		addressRepostory.delete(address);
	}

	@Override
	public void updateCustomerAddress(Address address) {
		addressRepostory.save(address);
	}

}
