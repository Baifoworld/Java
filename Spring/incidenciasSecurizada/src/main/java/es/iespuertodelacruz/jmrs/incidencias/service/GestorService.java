package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.repository.GestorRepository;

@Service
public class GestorService implements GenericService<Gestore, String>{

	
	@Autowired
	GestorRepository gestorRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Gestore> findAll() {
		return gestorRepository.findAll();
	}

	@Override
	public Page<Gestore> findAll(Pageable pageable) {
		return gestorRepository.findAll(pageable);
	}

	@Override
	public Optional<Gestore> findById(String id) {
		return gestorRepository.findById(id);
	}

	@Override
	public Gestore save(Gestore obj) {
		return gestorRepository.save(obj);
	}

	@Override
	public void deleteById(String id) {
		gestorRepository.deleteById(id);
	}

}
