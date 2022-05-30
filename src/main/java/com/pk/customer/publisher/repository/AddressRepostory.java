package com.pk.customer.publisher.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pk.customer.publisher.model.Address;

@Repository("addressRepostory")
public interface AddressRepostory extends JpaRepository<Address, Long> {

	/*
	 * @Query("select a from address a where customer.customer_id = :customerId")
	 * Address findByCustomer(Integer customerId);
	 */

	 @Query(value = "SELECT a from Address a where a.customer.customer_id =:customerId ", nativeQuery = true) 
	Address findByCustomer(@PathParam("customerId") Integer customerId);
}
