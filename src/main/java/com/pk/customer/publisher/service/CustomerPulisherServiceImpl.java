package com.pk.customer.publisher.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pk.customer.publisher.converts.CustomerPublisherConverts;
import com.pk.customer.publisher.dto.AddressDto;
import com.pk.customer.publisher.dto.CustomerDto;
import com.pk.customer.publisher.exception.CustomerNotFoundException;
import com.pk.customer.publisher.kafka.CustomerPulisher;
import com.pk.customer.publisher.model.Address;
import com.pk.customer.publisher.model.Customer;
import com.pk.customer.publisher.repository.CustomerPublisherDao;

@Transactional
@Service("customerService")
public class CustomerPulisherServiceImpl implements CustomerPublisherService {

	private static final Logger log = LoggerFactory.getLogger(CustomerPulisherServiceImpl.class);

	@Autowired
	private CustomerPublisherConverts converts;

	@Autowired
	private CustomerPublisherDao publisherDao;

	@Autowired
	private CustomerPulisher customerPulisher;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String saveCustomer(CustomerDto customerDto) {
		log.info("save customer " + customerDto.toString());

		Integer custId = publisherDao.saveCustomer(converts.convertDtoTOEntity(customerDto));
		if (custId != null) {
			Address address = converts.convertAddrestDtoToEntity(customerDto.getAddressDto());
			address.setCustId(custId);
			publisherDao.saveAddress(address);

			CustomerDto kafkaCustomer = converts.convertEntityToDto(publisherDao.getCustomerByCustId(custId));
			@SuppressWarnings("removal")
			Address custAddress = publisherDao.getCustomerAddressByCustId(new Integer(kafkaCustomer.getId()));
			AddressDto addressDto = converts.convertAddrestEntityToDto(custAddress);
			kafkaCustomer.setAddressDto(addressDto);

			String kafkaMsg = customerPulisher.sendJsonToConsumer(kafkaCustomer);
			if (kafkaMsg == null) {
				return "Customer Save  Successfully and Send data to Consumer is Failure";
			}
		}

		return " Customer Save and Send data into Consumer Successfully ";

	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		log.info("getAllCustomers method is started");
		List<Customer> custList = publisherDao.getAllCustomers();
		List<CustomerDto> customerDtosList = new ArrayList<CustomerDto>();
		custList.stream().forEach(c -> {
			CustomerDto customerDto = converts.convertEntityToDto(c);
			AddressDto addressDto = converts
					.convertAddrestEntityToDto(publisherDao.getCustomerAddressByCustId(c.getId()));
			customerDto.setAddressDto(addressDto);
			customerDtosList.add(customerDto);
		});
		log.info("getAllCustomers method is ended");
		return customerDtosList;
	}

	@Override
	public CustomerDto getCustomerById(Integer custId) {
		log.info("getCustomerById method is started");
		CustomerDto customerDto = null;
		try {
			Customer customer = getCustomer(custId);
			customerDto = converts.convertEntityToDto(customer);
			Address address = publisherDao.getCustomerAddressByCustId(customer.getId());
			if (address.getAddressId() != null) {
				AddressDto addressDto = converts.convertAddrestEntityToDto(address);
				customerDto.setAddressDto(addressDto);
			}

		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
			log.info("getCustomerById method exception occured" + e.getStackTrace());
		}
		log.info("getCustomerById method is end");
		return customerDto;

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String updateCustomer(Integer custId, CustomerDto customerDto) {
		log.info("updateCustomer method is started");
		Customer currentCustomer;
		try {
			currentCustomer = getCustomer(custId);
			if (currentCustomer.getId() != null) {
				publisherDao.updateCustomer(converts.convertDtoTOEntity(customerDto));
				Address address = converts.convertAddrestDtoToEntity(customerDto.getAddressDto());
				address.setCustId(currentCustomer.getId());
				publisherDao.updateCustomerAddress(address);
				return "Customer Updated Successfully";
			}
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
			log.info("updateCustomer method exception occured" + e.getStackTrace());
		}
		log.info("updateCustomer method is end");
		return "Customer Not Found!";

	}

	@Transactional
	@Override
	public String deleteCustomer(Integer custId) {
		log.info("deleteCustomer method is end " + custId);
		try {
			Customer deleteCustomer = getCustomer(custId);
			Address address = publisherDao.getCustomerAddressByCustId(deleteCustomer.getId());
			publisherDao.deleteCustomer(deleteCustomer, address);
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		log.info("deleteCustomer method is end");
		return "Customer Delete Successfully";

	}

	private Customer getCustomer(Integer custId) throws CustomerNotFoundException {
		Customer customer = publisherDao.getCustomerByCustId(custId);
		if (customer.getCustomerId() == null) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return customer;

	}

}
