package es.iespuertodelacruz.jmrs.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, String> {

}
