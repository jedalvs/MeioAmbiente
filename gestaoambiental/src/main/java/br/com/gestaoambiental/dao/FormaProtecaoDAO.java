package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.FormaProtecao;

public interface FormaProtecaoDAO {
	
	public void save(FormaProtecao formaProtecao);
	public void update(FormaProtecao formaProtecao);
	public void delete(int id);
	public List<FormaProtecao> findAll();
	public FormaProtecao load(int id);

}
