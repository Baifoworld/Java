package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.repository.TicketRepository;

@Service
public class TicketService implements GenericService<Ticket,Integer>{
	@Autowired
	TicketRepository ticketRepository;
	


	@Override
	public Optional<Ticket> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Ticket save(Ticket obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
