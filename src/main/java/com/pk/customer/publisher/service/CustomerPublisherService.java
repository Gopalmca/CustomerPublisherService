package com.pk.customer.publisher.service;

import java.util.List;

import com.pk.customer.publisher.dto.CustomerDto;

public interface CustomerPublisherService {

	public String saveCustomer(CustomerDto customerDto);

	public List<CustomerDto> getAllCustomers();

	public CustomerDto getCustomerById(Integer custId);
	
	public String updateCustomer(Integer custId, CustomerDto customerDto) ;

	public String deleteCustomer(Integer custId);
}
