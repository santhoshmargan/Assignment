package com.santhosh.book.ticket.app.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santhosh.book.ticket.app.entities.RestaurantTable;
import com.santhosh.book.ticket.app.entities.TableRequest;
import com.santhosh.book.ticket.app.dao.TableBookingDao;

@Service
public class TableBookingService {
	
	@Autowired
	private TableBookingDao tableBookingDao;

	public String bookTable(TableRequest tableRequest) {

		LocalDate date = LocalDate.now();
		LocalDate requestDate = tableRequest.getTimeOfReservation();

		if (requestDate.getDayOfMonth() != date.getDayOfMonth() ||
				requestDate.getMonth() != date.getMonth()) {
			return "We allow booking only on current day!";
		}
		
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		if (hour >= 20) {
			 return "Booking time is over!!";
		}
		
		 List<RestaurantTable> response = (List<RestaurantTable>) tableBookingDao.findAll();
		 List<RestaurantTable> tables = new ArrayList<>();
		 while(response.iterator().hasNext()) {
			 tables.add((RestaurantTable) response.iterator().next());
			 if(tables.size() >= 10) {
				 break;
			 }
		 }
		 
			// generating tables and storing to db
			if (tables.size() <= 0) {
				List<RestaurantTable> tableToStore = generateTables();
				for(RestaurantTable tempObj : tableToStore) {
					tableBookingDao.save(tempObj);
				}
			}
			
			List<RestaurantTable> currentTablesAvl = (List<RestaurantTable>) tableBookingDao.findAll();
			RestaurantTable tableRemaining = getRemainingTables(currentTablesAvl);
			RestaurantTable saveTable = new RestaurantTable();
		if((tableRemaining.getFourCapacityRemanining() != null
				|| tableRemaining.getTwoCapacityRemanining() != null)
				&& (tableRemaining.getFourCapacityRemanining().SIZE > 0
				|| tableRemaining.getTwoCapacityRemanining().SIZE > 0)) {
		for (RestaurantTable tempTables : currentTablesAvl) {
			if (tableRemaining.getFourCapacityRemanining() != null 
					&& tableRemaining.getFourCapacityRemanining() <= 5 
					&& tableRequest.getPeopleCount() != null
					&& tableRequest.getPeopleCount() <= 4  
					&& tableRequest.getPeopleCount() > 2
					&& tempTables.getTableNo() <= 5) {

				if (tempTables.getMobileNumber() == null) {
					saveTable.setTableNo(tempTables.getTableNo());
					saveTable.setUserName(tableRequest.getUserName());
					saveTable.setMobileNumber(tableRequest.getMobileNumber());
					saveTable.setPeopleCount(tableRequest.getPeopleCount());
					saveTable.setTimeOfReservation(time);
					saveTable.setTableSize(4);
				} else {
					continue;
				}
				break;
			} else if (tableRemaining.getTwoCapacityRemanining() != null 
					&& tableRemaining.getTwoCapacityRemanining() <= 5 
					&& tableRequest.getPeopleCount() != null
					&& tableRequest.getPeopleCount() <= 2 
					&& tableRequest.getPeopleCount() > 0
					&& tempTables.getTableNo() > 5) {
				
				if (tempTables.getMobileNumber() == null) {
					saveTable.setTableNo(tempTables.getTableNo());
					saveTable.setUserName(tableRequest.getUserName());
					saveTable.setMobileNumber(tableRequest.getMobileNumber());
					saveTable.setPeopleCount(tableRequest.getPeopleCount());
					saveTable.setTimeOfReservation(time);
					saveTable.setTableSize(2);
				} else {
					continue;
				}
				break;
			} else {
				continue;
			}
		}
		} else {
			return "Tables not available";
		}
		
		if (saveTable.getMobileNumber() == null) {
			return "Tables not available";
		} else {
			tableBookingDao.save(saveTable);
		}
		return "Success";
	}

	public String cancelTableByMobileNo(TableRequest table) {
		tableBookingDao.deleteByMobileNumber(table.getMobileNumber());
		return "Success";
	}

	public String updateTicket(TableRequest updateRequest) {
		RestaurantTable findResponse = tableBookingDao.getByMobileNumber(updateRequest.getMobileNumber());
		if (findResponse.getMobileNumber() != null && updateRequest.getMobileNumber() != null
				&& findResponse.getMobileNumber().equals(updateRequest.getMobileNumber())) {
			findResponse.setPeopleCount(updateRequest.getPeopleCount());
			findResponse.setMobileNumber(updateRequest.getMobileNumber());
			findResponse.setTimeOfReservation(LocalTime.now());
			List<RestaurantTable> currentTablesAvl = (List<RestaurantTable>) tableBookingDao.findAll();
			RestaurantTable tableRemaining = getRemainingTables(currentTablesAvl);
			
			if((tableRemaining.getFourCapacityRemanining() != null
					|| tableRemaining.getTwoCapacityRemanining() != null)
					&& (tableRemaining.getFourCapacityRemanining().SIZE > 0
					|| tableRemaining.getTwoCapacityRemanining().SIZE > 0)) {
			for (RestaurantTable tempTables : currentTablesAvl) {
				if (tableRemaining.getFourCapacityRemanining() != null 
						&& tableRemaining.getFourCapacityRemanining() <= 5 
						&& updateRequest.getPeopleCount() != null
						&& updateRequest.getPeopleCount() <= 4  
						&& updateRequest.getPeopleCount() > 2
						&& tempTables.getTableNo() <= 5) {

					if (tempTables.getMobileNumber() == null) {
						findResponse.setPeopleCount(updateRequest.getPeopleCount());
						findResponse.setTimeOfReservation(LocalTime.now());
						findResponse.setTableSize(4);
					} else {
						continue;
					}
					break;
				} else if (tableRemaining.getTwoCapacityRemanining() != null 
						&& tableRemaining.getTwoCapacityRemanining() <= 5 
						&& updateRequest.getPeopleCount() != null
						&& updateRequest.getPeopleCount() <= 2 
						&& updateRequest.getPeopleCount() > 0
						&& tempTables.getTableNo() > 5) {
					
					if (tempTables.getMobileNumber() == null) {
						findResponse.setPeopleCount(updateRequest.getPeopleCount());
						findResponse.setTimeOfReservation(LocalTime.now());
						findResponse.setTableSize(2);
					} else {
						continue;
					}
					break;
				} else {
					continue;
				}
			}
			} else {
				return "Tables not available";
			}
			
			if (findResponse.getMobileNumber() == null) {
				return "Tables not available";
			} else {
				tableBookingDao.save(findResponse);
			}
		} else {
			return "Invalid request!!";
		}
		return "Success";
	}

	public Iterable<RestaurantTable> findAllTable() {
		Iterable<RestaurantTable> tables = tableBookingDao.findAll();
		return tables;
	}

	// get remaining tables
	private RestaurantTable getRemainingTables(Iterable<RestaurantTable> tablesBooked) {
		int i = 1;
		int j = 1;
		RestaurantTable tablesRemaining = new RestaurantTable();
		for (RestaurantTable tempTables : tablesBooked) {
			if (tempTables.getTableNo() < 6 && tempTables.getMobileNumber() == null) {
				tablesRemaining.setFourCapacityRemanining(i++);
			}
			if (tempTables.getTableNo() > 5 && tempTables.getMobileNumber() == null) {
				tablesRemaining.setTwoCapacityRemanining(j++);
			}
		}
		return tablesRemaining;
	}
	
	// Generating tables at first book table api call
	private static List<RestaurantTable> generateTables() {
		int n = 0;
		int i = 1;
		
		List<RestaurantTable> list = new ArrayList<>();
		while (n < 10) {
			RestaurantTable table = new RestaurantTable();
			if (i <= 5) {
				table.setTableNo(i);
				table.setTableSize(4);
			} else {
				table.setTableNo(i);
				table.setTableSize(2);
			}
			list.add(table);
			i++;
			n++;
		}
		return list;
	}
}
