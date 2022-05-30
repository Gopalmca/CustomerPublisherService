package com.pk.customer.publisher.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "CUSTOMER_INFO")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@JsonProperty("CustomerId")
	private String customerId = null;

	@JsonProperty("firstName")
	private String firstName = null;

	@JsonProperty("lastName")
	private String lastName = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("phone")
	private String phone = null;

	@JsonProperty("country")
	private String country = null;

	@JsonProperty("countryCode")
	private String countryCode = null;

	@JsonProperty("birthDate")
	private LocalDate birthDate = null;

	public Customer() {
	}

	public Customer(Integer Id, String customerId, String firstName, String lastName, String email, String phone,
			String country, String countryCode, LocalDate birthDate, CustomerstatusEnum customerstatus) {
		super();
		this.Id = Id;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.country = country;
		this.countryCode = countryCode;
		this.birthDate = birthDate;
		this.customerstatus = customerstatus;
	}

	/**
	 * Customer Status
	 */
	public enum CustomerstatusEnum {
		O("Open"),

		S("Suspended"),

		R("Restored"),

		C("Closed");

		private String value;

		CustomerstatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static CustomerstatusEnum fromValue(String text) {
			for (CustomerstatusEnum b : CustomerstatusEnum.values()) {
				if (String.valueOf(b.name()).equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}

		@JsonCreator
		public static CustomerstatusEnum fromValueEnum(String text) {
			for (CustomerstatusEnum b : CustomerstatusEnum.values()) {
				if (String.valueOf(b.value).equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("customerstatus")
	private CustomerstatusEnum customerstatus = null;

	public Customer customerId(String customerId) {
		this.customerId = customerId;
		return this;
	}

	/**
	 * Get Id
	 * 
	 * @return Id
	 **/
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Customer Id(Integer Id) {
		this.Id = Id;
		return this;
	}

	/**
	 * Get customerId
	 * 
	 * @return customerId
	 **/

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Customer firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Get firstName
	 * 
	 * @return firstName
	 **/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Customer lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Get lastName
	 * 
	 * @return lastName
	 **/
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * 
	 * @return email
	 **/
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer phone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * Get phone
	 * 
	 * @return phone
	 **/

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * Get country
	 * 
	 * @return country
	 **/

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Customer countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * Get countryCode
	 * 
	 * @return countryCode
	 **/

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Get birthDate
	 * 
	 * @return birthDate
	 **/

	@Valid
	public Customer customerstatus(CustomerstatusEnum customerstatus) {
		this.customerstatus = customerstatus;
		return this;
	}

	/**
	 * Customer Status
	 * 
	 * @return customerstatus
	 **/

	public CustomerstatusEnum getCustomerstatus() {
		return customerstatus;
	}

	public void setCustomerstatus(CustomerstatusEnum customerstatus) {
		this.customerstatus = customerstatus;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Customer customer = (Customer) o;
		return Objects.equals(this.customerId, customer.customerId)
				&& Objects.equals(this.firstName, customer.firstName)
				&& Objects.equals(this.lastName, customer.lastName) && Objects.equals(this.email, customer.email)
				&& Objects.equals(this.phone, customer.phone) && Objects.equals(this.country, customer.country)
				&& Objects.equals(this.countryCode, customer.countryCode)
				&& Objects.equals(this.birthDate, customer.birthDate)
				&& Objects.equals(this.customerstatus, customer.customerstatus);

	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, firstName, lastName, email, phone, country, countryCode, birthDate,
				customerstatus);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + ", country=" + country + ", countryCode=" + countryCode + ", birthDate="
				+ birthDate + ", customerstatus=" + customerstatus + "]";
	}

}
