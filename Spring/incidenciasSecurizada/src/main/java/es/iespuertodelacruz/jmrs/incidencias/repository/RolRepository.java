package es.iespuertodelacruz.jmrs.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jmrs.incidencias.entity.Role;


public interface RolRepository extends JpaRepository<Role, Integer>{
	
}
