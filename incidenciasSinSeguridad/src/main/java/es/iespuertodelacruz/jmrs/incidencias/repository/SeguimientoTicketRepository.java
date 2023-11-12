package es.iespuertodelacruz.jmrs.incidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;

public interface SeguimientoTicketRepository extends JpaRepository<SeguimientoTicket, Integer> {
/*
	@Query("SELECT st FROM SeguimientoTickets st where st.id_ticket = :idTicket")
	List<SeguimientoTicket> findByIdTicket(@Param("idTicket") Integer id_Ticket);*/
}
