package es.iespuertodelacruz.jmrs.incidencias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, String>{
	@Query("SELECT c FROM Cliente c where c.usuario.username = :username")
	Optional<Cliente> findByNick(@Param("username") String username);
}
