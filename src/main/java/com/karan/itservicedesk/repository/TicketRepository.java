package com.karan.itservicedesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karan.itservicedesk.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
