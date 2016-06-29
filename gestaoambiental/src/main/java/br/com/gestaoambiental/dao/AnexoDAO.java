package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.Acesso;

public interface AnexoDAO {
	
	public void save(Acesso acesso);
	public void update(Acesso acesso);
	public void delete(int id);
	public List<Acesso> findAll();
	public Acesso load(int id);

}
