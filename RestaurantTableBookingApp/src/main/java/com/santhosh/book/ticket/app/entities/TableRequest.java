package com.santhosh.book.ticket.app.entities;

import java.time.LocalDate;

public class TableRequest {
	
	private String userName;
	private Integer mobileNumber;
	private Integer peopleCount;
	private LocalDate timeOfReservation;
	
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
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public LocalDate getTimeOfReservation() {
		return timeOfReservation;
	}
	public void setTimeOfReservation(LocalDate timeOfReservation) {
		this.timeOfReservation = timeOfReservation;
	}
	
	

}
