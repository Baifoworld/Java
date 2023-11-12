package es.iespuertodelacruz.jmrs.incidencias.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<T, E> {
	Iterable<T> findAll();

	Page<T> findAll(Pageable pageable);

	Optional<T> findById(E id);

	T save(T obj);

	void deleteById(E id);
}