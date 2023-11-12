package es.iespuertodelacruz.jmrs.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService <T,E>{
	Iterable<T> findAll();
	Page<T> findAll(Pageable page);
	Optional<T> findById(E id);
	T save(T producto);
	void deleteById(E id);

}
