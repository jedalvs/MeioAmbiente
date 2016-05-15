package br.com.gestaoambiental.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<E, ID extends Serializable> {
	
	void save(E entity) throws Exception;

	void delete(E entity) throws Exception;

	void update(E entity) throws Exception;

	E load(ID key) throws Exception;

	List<E> findAll() throws Exception;


}
