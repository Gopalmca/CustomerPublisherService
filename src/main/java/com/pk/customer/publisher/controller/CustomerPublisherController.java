package com.pk.customer.publisher.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.customer.publisher.dto.CustomerDto;
import com.pk.customer.publisher.service.CustomerPublisherService;

@RestController
@RequestMapping("/customer")
public class CustomerPublisherController {

	private Log logger = LogFactory.getLog(CustomerPublisherController.class);
	@Autowired
	private CustomerPublisherService customerService;

	@PostMapping(value = "/savecustomer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
		logger.info("save customer method called " + customerDto);
		return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
	}

	@GetMapping(path = "/getallcustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDto>> getAllCustomer() {
		logger.info("getall customer is called ");
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping(path = "/getcustomerbycustid/{custid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDto> getCustomerByCustId(@PathVariable("custid") Integer custId) {
		logger.info("getCustomerByCustId method is called" + custId);
		return new ResponseEntity<>(customerService.getCustomerById(custId), HttpStatus.OK);
	}

	@PutMapping(value = "/updatecustomer/{custid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateCustomer(@PathVariable(name = "custid") Integer custId,
			@RequestBody @Valid CustomerDto customerDto) {
		logger.info("updateCustomer method is called" + customerDto);
		return new ResponseEntity<>(customerService.updateCustomer(custId, customerDto), HttpStatus.OK);
	}

	@GetMapping(value = "/deletecustomer/{custid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCustomerByCustId(@PathVariable("custid") Integer custId) {
		logger.info("deleteCustomerByCustId method is called" + custId);
		return new ResponseEntity<>(customerService.deleteCustomer(custId), HttpStatus.OK);
	}

}
