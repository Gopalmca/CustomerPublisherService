package com.pk.customer.publisher.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pk.customer.publisher.model.Customer;
import com.pk.customer.publisher.model.Customer.CustomerstatusEnum;
import com.pk.customer.publisher.repository.CustomerReposistry;
import com.pk.customer.publisher.service.CustomerPulisherServiceImpl;

@SpringBootTest
public class CustomerPublisherServiceTest {
	@InjectMocks
	private CustomerPulisherServiceImpl customerService;

	@Mock
	CustomerReposistry customerReposistry;

	@SuppressWarnings("deprecation")
	@Before(value = "")
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllCustomerTest() {
		List<Customer> custList = new ArrayList<>();
		Customer cust1 = new Customer(1, "C00001", "Gopal", "P", "Gopal@gmail.com", "+91 9493384380", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.O);
		Customer cust2 = new Customer(1, "COOO2", "Harish", "M", "Harish@gmail.com", "+91 9493384280", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.C);

		custList.add(cust1);
		custList.add(cust2);

		when(customerReposistry.findAll()).thenReturn(custList);

	}

	@Test
	public void getCustomerByIdCustIdTest() {

		Customer cust = new Customer(1, "C00001", "Gopal", "P", "Gopal@gmail.com", "+91 9493384380", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.O);
		Mockito.when(customerReposistry.findById(cust.getId())).thenReturn(java.util.Optional.of(cust));

	}

	@Test
	public void deleteCustomerTest() {
		Customer cust = new Customer(1, "C00001", "Harish", "M", "Harish@gmail.com", "+91 9493384280", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.C);
		Mockito.when(customerReposistry.findById(cust.getId())).thenReturn(java.util.Optional.of(cust));
	}

	@Test
	public void saveCustomerTest() {
		Customer cust2 = new Customer(1, "C00001", "Harish", "M", "Harish@gmail.com", "+91 9493384280", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.C);

		when(customerReposistry.save(cust2)).thenReturn(cust2);

		assertEquals("C00001", cust2.getCustomerId());
		assertEquals("Harish", cust2.getFirstName());
		assertEquals("Harish@gmail.com", cust2.getEmail());

	}

	@Test
	public void updateCustTest() {
		Customer cust = new Customer(2, "C00002", "Gopal", "P", "Gopal@gmail.com", "+91 9493384380", "India", "IN",
				LocalDate.of(1993, 05, 17), CustomerstatusEnum.O);
		when(customerReposistry.save(cust)).thenReturn(cust);

		assertEquals("C00002", cust.getCustomerId());
		assertEquals("Gopal", cust.getFirstName());
		assertEquals("Gopal@gmail.com", cust.getEmail());
	}
}
