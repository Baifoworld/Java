package es.iespuertodelacruz.jmrs.repository;
import org.springframework.data.jpa.repository.*;
import es.iespuertodelacruz.jmrs.entity.Ticket;
public interface TicketRepository extends JpaRepository <Ticket, Integer>{

}
