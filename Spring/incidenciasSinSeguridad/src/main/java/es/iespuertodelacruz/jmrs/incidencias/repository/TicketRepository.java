package es.iespuertodelacruz.jmrs.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
