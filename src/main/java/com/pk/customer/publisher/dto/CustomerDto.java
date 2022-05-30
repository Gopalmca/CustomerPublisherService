package com.pk.customer.publisher.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CustomerDto {

	private String id;

	@NotBlank(message = "CustomerNumber is Required")
	private String customerId;

	@NotBlank(message = "FirstName is Required")
	@Max(value = 50)
	private String firstName;

	@NotBlank(message = "LastName is Required")
	@Max(value = 50)
	private String lastName;

	@Email
	private String email;

	@Min(value = 10, message = "Mobile Number should be 10 digits")
	@Max(value = 10, message = "Mobile Number should be 10 digits")
	private String mobileNo;

	@NotBlank
	private String country;

	@NotBlank
	@Max(value = 2, message = "")
	private String countryCode;

	@NotBlank
	private String birthdate;

	@NotBlank
	private String customerStatus;

	private AddressDto addressDto;

	public CustomerDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(@NotBlank String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}
}
