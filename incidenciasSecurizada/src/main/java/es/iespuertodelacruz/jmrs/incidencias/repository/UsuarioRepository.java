package es.iespuertodelacruz.jmrs.incidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u where u.username = :username")
	Usuario findByNick(@Param("username") String username);
}
