package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.repository.UsuarioRepository;

@Service
public class UsuarioService implements GenericService<Usuario, Integer>{

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}
	
	@Override
	public Usuario save(Usuario obj) {
		return usuarioRepository.save(obj);
	}

	@Override
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Usuario findByNick(String nick) {
		Usuario u = null;
		u = usuarioRepository.findByNick(nick);
		
		return u;
	}

}
