package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import es.iespuertodelacruz.jmrs.incidencias.entity.Role;
import es.iespuertodelacruz.jmrs.incidencias.repository.RolRepository;

@Service
public class RolService implements GenericService<Role, Integer>{

	
	@Autowired
	RolRepository rolRepository;

	@Override
	public Iterable<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return rolRepository.findById(id);
	}

	@Override
	public Role save(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
