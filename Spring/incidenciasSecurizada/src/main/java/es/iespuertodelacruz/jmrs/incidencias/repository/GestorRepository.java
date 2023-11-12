package es.iespuertodelacruz.jmrs.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;

public interface GestorRepository extends JpaRepository<Gestore, String> {

}
