package com.karan.itservicedesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karan.itservicedesk.dto.TicketResponseDTO;
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
	
	public Ticket findTicketById(Long id) {
		return ticketRepository.findById(id).orElse(null);
	}
	
	public Ticket updateTicket(Long id, Ticket ticket) {
		
		Ticket existingTicket = ticketRepository.findById(id).orElse(null);
		
		if(existingTicket == null) {
			
			return null;
		}
		
		existingTicket.setTitle(ticket.getTitle());
		existingTicket.setDescription(ticket.getDescription());
		existingTicket.setCategory(ticket.getCategory());
        existingTicket.setPriority(ticket.getPriority());
        existingTicket.setStatus(ticket.getStatus());
        
        return ticketRepository.save(existingTicket);
	}
	
	public boolean deletedTicket(Long id) {
		
		if(!ticketRepository.existsById(id)) {
			return false;
		}
		
		ticketRepository.deleteById(id);
		return true;
	}
	
	public TicketResponseDTO convertToDTO(Ticket ticket) {
	
		TicketResponseDTO dto = new TicketResponseDTO();
		
		dto.setId(ticket.getId());
		dto.setTitle(ticket.getTitle());
		dto.setDescription(ticket.getDescription());
		dto.setCategory(ticket.getCategory());
		dto.setPriority(ticket.getPriority());
		dto.setStatus(ticket.getStatus());
		dto.setCreatedAt(ticket.getCreatedAt());
		
		if(ticket.getUser() != null) {
			
			dto.setUserId(ticket.getUser().getId());
		}
		
		return dto;
	}
}
