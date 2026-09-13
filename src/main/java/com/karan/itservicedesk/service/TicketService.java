package com.karan.itservicedesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karan.itservicedesk.model.Ticket;
import com.karan.itservicedesk.repository.TicketRepository;

@Service
public class TicketService {
	
	private final TicketRepository ticketRepository;
	
	public TicketService( TicketRepository ticketRepository) {
		
		this.ticketRepository = ticketRepository;
	}
	
	public Ticket saveTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public List<Ticket> findAllTickets(){
		
		return ticketRepository.findAll();
	}
}
