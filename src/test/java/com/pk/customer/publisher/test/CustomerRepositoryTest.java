package com.pk.customer.publisher.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pk.customer.publisher.model.Customer;
import com.pk.customer.publisher.model.Customer.CustomerstatusEnum;
import com.pk.customer.publisher.repository.CustomerReposistry;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerReposistry customerReposistry;

	private Customer getCustomer() {
		Customer cust = new Customer(1, "", "Ravi", "M", "Ravih@gmail.com", "+91 9493384280", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.C);
		return cust;
	}

	@Test
	public void saveCustomerTest() {

		Customer cust = customerReposistry.save(getCustomer());
		assertNotNull(cust);
		assertTrue(cust.getId() > 0);

	}

	@Test
	public void getCustomerByCustIdTest() {

		Customer customer = customerReposistry.save(getCustomer());
		Customer getCustomer = customerReposistry.getById(customer.getId());
		assertThat(getCustomer).isEqualTo(customer);

	}

	@Test
	public void getAllCustomers() {

		List<Customer> custList = customerReposistry.findAll();
		assertThat(custList.size()).isGreaterThan(0);

	}

	@Test
	public void updateCustomerTest() {
		Customer customer = customerReposistry.findById(11).get();

		customer.setPhone("+91 1234567890");

		Customer updateCustomer = customerReposistry.findById(11).get();
		assertThat(updateCustomer.getPhone()).isEqualTo("+91 1234567890");

	}

	@Test
	public void deleteCustomerTest() {
		Customer customer = customerReposistry.findById(12).get();
		customerReposistry.deleteById(customer.getId());

		List<Customer> cList = new ArrayList<>();

		customerReposistry.findAll().forEach(c -> cList.add(c));
		assertThat(cList.size()).isGreaterThan(0);
	}

}
