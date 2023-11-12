package repositories;

import java.util.List;

public interface ICRUD<T,E> {
	List<T> findAll();
	
	T findById(E id);
	
	T save(T obj);
	
	T update(T obj);
	
	T findByName(E string);
	
	boolean delete(E id);
}
