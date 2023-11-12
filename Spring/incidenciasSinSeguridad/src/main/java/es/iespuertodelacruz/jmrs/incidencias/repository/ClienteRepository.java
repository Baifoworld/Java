package es.iespuertodelacruz.jmrs.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
