package com.santhosh.book.ticket.app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santhosh.book.ticket.app.entities.RestaurantTable;
import com.santhosh.book.ticket.app.entities.TableRequest;
import com.santhosh.book.ticket.app.service.TableBookingService;

@RestController
@RequestMapping(value="/api/tables")
public class TableBookingController {
	
	 private Log log = LogFactory.getLog(TableBookingController.class);
	
		@Autowired
		private TableBookingService tableBookingService;
		
		//To Create a new Ticket (note:ableId is auto Generated)
	    @PostMapping(value="/bookTable", consumes="application/json")
		public String bookTable(@RequestBody TableRequest table)
		{
			return tableBookingService.bookTable(table);
		}
	    
		
		// Cancel ticket
		@DeleteMapping(value = "/cancelTable")
		public String getTicketByMobile(@RequestBody TableRequest table) {
			try {
				return tableBookingService.cancelTableByMobileNo(table);
			} catch (Exception ex) {
				log.error("Exception:" + ex.getMessage(), ex);
			}
			return null;
		}

		// Updating the ticket
		@PutMapping(value = "/updateTable")
		public String updateTable(@RequestBody TableRequest table) {

			return tableBookingService.updateTicket(table);
		}

		@GetMapping(value = "/findAllTable")
		public Iterable<RestaurantTable> findAllTable() {
			return tableBookingService.findAllTable();
		}

	}
