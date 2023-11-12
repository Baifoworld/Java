package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.repository.GestorRepository;

@Service
public class GestorService implements GenericService<Gestor, String>{

	
	@Autowired
	GestorRepository gestorRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Gestor> findAll() {
		return gestorRepository.findAll();
	}

	@Override
	public Page<Gestor> findAll(Pageable pageable) {
		return gestorRepository.findAll(pageable);
	}

	@Override
	public Optional<Gestor> findById(String id) {
		return gestorRepository.findById(id);
	}

	@Override
	public Gestor save(Gestor obj) {
		return gestorRepository.save(obj);
	}

	@Override
	public void deleteById(String id) {
		gestorRepository.deleteById(id);
	}

}
