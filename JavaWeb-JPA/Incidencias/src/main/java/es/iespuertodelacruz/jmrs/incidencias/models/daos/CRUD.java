package es.iespuertodelacruz.jmrs.incidencias.models.daos;

import java.util.ArrayList;

//T objeto de java, E es el id PK del objeto de la DDBB
public interface CRUD <T,E> {
	
	T save(T dao);
	T findById(E id);
	boolean update(T dao);
	boolean delete(E id);
	ArrayList<T> selectAll();
}
