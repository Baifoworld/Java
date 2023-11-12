package es.iespuertodelacruz.jmrs.incidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	@Query("SELECT t FROM Ticket t WHERE t.fechaFin IS null")
	List<Ticket> findOpenTickets();
	
	@Query("SELECT t FROM Ticket t WHERE t.fechaFin IS null AND t.cliente.idCliente = :idCliente")
	List<Ticket> findOpenTicketsClient(@Param("idCliente")String idCliente);
}
