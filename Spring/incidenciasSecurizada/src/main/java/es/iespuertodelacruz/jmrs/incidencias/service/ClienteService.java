package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.repository.ClienteRepository;

@Service
public class ClienteService implements GenericService<Cliente, String>{

	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	public Optional<Cliente> findById(String id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente save(Cliente obj) {
		return clienteRepository.save(obj);
	}

	@Override
	public void deleteById(String id) {
		clienteRepository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<Cliente> findByNick(String nick) {
		Cliente c = null;
		return clienteRepository.findByNick(nick);
		
	}

}
