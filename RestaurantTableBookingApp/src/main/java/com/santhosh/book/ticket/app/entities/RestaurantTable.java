package com.santhosh.book.ticket.app.entities;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RestaurantTable {

	@Id
	@Column(name = "tableNo")
	private Integer tableNo;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "mobileNumber")
	private Integer mobileNumber;

	@Column(name = "timeOfReservation")
	private LocalTime timeOfReservation;

	@Column(name = "peopleCount")
	private Integer peopleCount;
	
	@Column(name = "tableSize")
	private Integer tableSize;
	
	@Column(name = "fourCapacityRemanining")
	private Integer fourCapacityRemanining;
	
	@Column(name = "twoCapacityRemanining")
	private Integer twoCapacityRemanining;
	
	

	public Integer getTableSize() {
		return tableSize;
	}

	public void setTableSize(Integer tableSize) {
		this.tableSize = tableSize;
	}

	public Integer getTableNo() {
		return tableNo;
	}

	public void setTableNo(Integer tableNo) {
		this.tableNo = tableNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalTime getTimeOfReservation() {
		return timeOfReservation;
	}

	public void setTimeOfReservation(LocalTime timeOfReservation) {
		this.timeOfReservation = timeOfReservation;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Integer getFourCapacityRemanining() {
		return fourCapacityRemanining;
	}

	public void setFourCapacityRemanining(Integer fourCapacityRemanining) {
		this.fourCapacityRemanining = fourCapacityRemanining;
	}

	public Integer getTwoCapacityRemanining() {
		return twoCapacityRemanining;
	}

	public void setTwoCapacityRemanining(Integer twoCapacityRemanining) {
		this.twoCapacityRemanining = twoCapacityRemanining;
	}

}
