package es.iespuertodelacruz.jmrs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jmrs.entity.Cliente;
import es.iespuertodelacruz.jmrs.repository.ClienteRepository;

@Service
public class ClienteService implements IService<Cliente, String> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public Iterable<Cliente> findAll(){
		List<Cliente> findAll = clienteRepository.findAll();
		return null;
	}

	@Override
	public Optional<Cliente> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Cliente save(Cliente producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Cliente> findAll(Pageable page) {
		List<Cliente> findAll = clienteRepository.findAll();
		return null;
	}
	

}
