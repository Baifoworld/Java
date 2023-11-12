package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.repository.ClienteRepository;
import es.iespuertodelacruz.jmrs.incidencias.repository.TicketRepository;

@Service
public class TicketService implements GenericService<Ticket,Integer>{
	@Autowired
	TicketRepository ticketRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> findAll(Pageable pageable) {
		return ticketRepository.findAll(pageable);
	}

	@Override
	public Optional<Ticket> findById(Integer id) {
		return ticketRepository.findById(id);
	}

	@Override
	public Ticket save(Ticket obj) {
		return ticketRepository.save(obj);
	}

	@Override
	public void deleteById(Integer id) {
		ticketRepository.deleteById(id);
	}
}
