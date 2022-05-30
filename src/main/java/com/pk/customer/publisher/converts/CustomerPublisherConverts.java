package com.pk.customer.publisher.converts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.pk.customer.publisher.dto.AddressDto;
import com.pk.customer.publisher.dto.CustomerDto;
import com.pk.customer.publisher.model.Address;
import com.pk.customer.publisher.model.Customer;
import com.pk.customer.publisher.model.Customer.CustomerstatusEnum;

@Component
public class CustomerPublisherConverts {

	@SuppressWarnings("removal")
	public Customer convertDtoTOEntity(CustomerDto dto) {

		Customer customer = new Customer();
		if (dto.getId() != "" && dto.getId() != null) {
			customer.setId(new Integer(dto.getId()));
		}
		customer.setCustomerId(dto.getCustomerId());
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getMobileNo());
		customer.setCountry(dto.getCountry());
		customer.setCountryCode(dto.getCountryCode());
		customer.setCustomerstatus(CustomerstatusEnum.fromValue(dto.getCustomerStatus()));
		if (dto.getBirthdate() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dto.getBirthdate(), formatter);
			customer.setBirthDate(localDate);
		}
		return customer;

	}

	public Address convertAddrestDtoToEntity(AddressDto dto) {
		Address address = new Address();
		address.setAddressId(dto.getAddressId());
		address.setAddressLine1(dto.getAddressLine1());
		address.setAddressLine2(dto.getAddressLine2());
		address.setStreet(dto.getStreet());
		address.setPostalCode(dto.getPostalCode());

		return address;

	}

	// Convert Dto To Entity

	public CustomerDto convertEntityToDto(Customer customer) {

		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(String.valueOf(customer.getId()));
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNo(customer.getPhone());
		customerDto.setCountry(customer.getCountry());
		customerDto.setCountryCode(customer.getCountryCode());
		if (customer.getCustomerstatus() != null) {
			CustomerstatusEnum status = CustomerstatusEnum.fromValueEnum(customer.getCustomerstatus().toString());
			customerDto.setCustomerStatus(status.toString());
		}
		if (customer.getBirthDate() != null) {
			customerDto.setBirthdate(customer.getBirthDate().toString());
		}

		return customerDto;

	}

	public AddressDto convertAddrestEntityToDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressId(address.getAddressId());
		addressDto.setAddressLine1(address.getAddressLine1());
		addressDto.setAddressLine2(address.getAddressLine2());
		addressDto.setStreet(address.getStreet());
		addressDto.setPostalCode(address.getPostalCode());

		return addressDto;

	}

}
