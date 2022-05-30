package com.pk.customer.publisher.repository;

import java.util.List;

import com.pk.customer.publisher.model.Address;
import com.pk.customer.publisher.model.Customer;

public interface CustomerPublisherDao {

	public Integer saveCustomer(Customer customer);

	public void saveAddress(Address address);

	public Customer getCustomerByCustId(Integer custId);

	public List<Customer> getAllCustomers();

	public void updateCustomer(Customer customer);

	public Address getCustomerAddressByCustId(Integer custId);

	public void deleteCustomer(Customer customer,Address address);

	public void updateCustomerAddress(Address address);

}
