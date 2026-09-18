package com.karan.itservicedesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karan.itservicedesk.model.Ticket;
import com.karan.itservicedesk.service.TicketService;

import jakarta.validation.Valid;

@RestController
public class TicketController {
	
	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		
		this.ticketService = ticketService;
	}
	
	
	@PostMapping("/api/tickets")
	public Ticket createTicket(@Valid @RequestBody Ticket ticket) {
		
		return ticketService.saveTicket(ticket);
	}
	
	@GetMapping("/api/tickets")
	public List<Ticket> getAllTickets() {
		
		return ticketService.findAllTickets();
	}
	
	@GetMapping("api/tickets/{id}")
	public ResponseEntity<Ticket> getTicketBId(@PathVariable Long id)
	{
		Ticket ticket = ticketService.findTicketById(id);
		
		if(ticket == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ticket);
	}
	
	@PutMapping("api/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable Long id ,@Valid @RequestBody Ticket ticket) {
		
		Ticket updateTicket = ticketService.updateTicket(id, ticket);
		
		if(updateTicket == null) {
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(updateTicket);
	}
	
	@DeleteMapping("/api/tickets/{id}")
	public ResponseEntity<Void> deletedTicket( @PathVariable Long id) {
		
		boolean deleted = ticketService.deletedTicket(id);
		
		if(!deleted)
		{
		
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.noContent().build();
		
		}
}
