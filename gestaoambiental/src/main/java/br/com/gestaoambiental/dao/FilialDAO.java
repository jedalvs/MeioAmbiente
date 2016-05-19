package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.Filial;

public interface FilialDAO {
	
	public void save(Filial filial);
	public void update(Filial filial);
	public void delete(int id);
	public List<Filial> findAll();
	public Filial load(int id);

}
