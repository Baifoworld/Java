package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.repository.SeguimientoTicketRepository;

@Service
public class SeguimientoTicketService implements GenericService<SeguimientoTicket, Integer>{

	
	@Autowired
	SeguimientoTicketRepository seguiTicketRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<SeguimientoTicket> findAll() {
		return seguiTicketRepository.findAll();
	}

	@Override
	public Page<SeguimientoTicket> findAll(Pageable pageable) {
		return seguiTicketRepository.findAll(pageable);
	}

	@Override
	public Optional<SeguimientoTicket> findById(Integer id) {
		return seguiTicketRepository.findById(id);
	}

	@Override
	public SeguimientoTicket save(SeguimientoTicket obj) {
		return seguiTicketRepository.save(obj);
	}

	@Override
	public void deleteById(Integer id) {
		seguiTicketRepository.deleteById(id);
	}

}
