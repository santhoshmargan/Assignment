package com.santhosh.book.ticket.app.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.santhosh.book.ticket.app.entities.RestaurantTable;

@Repository
public interface TableBookingDao extends JpaRepository<RestaurantTable, Serializable>{
	
	@Transactional
	void deleteByMobileNumber(Integer mobileNumber);

	RestaurantTable getByMobileNumber(Integer mobileNumber);

}