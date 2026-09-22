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

import com.karan.itservicedesk.dto.TicketResponseDTO;
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
	public TicketResponseDTO createTicket(@Valid @RequestBody Ticket ticket) {
		
		Ticket savedTicket = ticketService.saveTicket(ticket);
		
		return ticketService.convertToDTO(savedTicket);
	}
	
	@GetMapping("/api/tickets")
	public List<TicketResponseDTO> getAllTickets() {
		
		return ticketService.findAllTickets()
				.stream()
				.map(ticketService::convertToDTO)
				.toList();
	}
	
	@GetMapping("api/tickets/{id}")
	public ResponseEntity<TicketResponseDTO> getTicketBId(@PathVariable Long id)
	{
		Ticket ticket = ticketService.findTicketById(id);
		
		if(ticket == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ticketService.convertToDTO(ticket));
	}
	
	@PutMapping("api/tickets/{id}")
	public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id ,@Valid @RequestBody Ticket ticket) {
		
		Ticket updateTicket = ticketService.updateTicket(id, ticket);
		
		if(updateTicket == null) {
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(ticketService.convertToDTO(updateTicket)
				);
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
