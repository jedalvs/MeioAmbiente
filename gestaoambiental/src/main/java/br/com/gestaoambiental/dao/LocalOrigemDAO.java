package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.LocalOrigem;

public interface LocalOrigemDAO {
	
	public void save(LocalOrigem localOrigem);
	public void update(LocalOrigem localOrigem);
	public void delete(int id);
	public List<LocalOrigem> findAll();
	public LocalOrigem load(int id);

}
