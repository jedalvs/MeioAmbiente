package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.ModuloRecuperacao;

public interface ModuloRecuperacaoDAO {
	
	public void save(ModuloRecuperacao moduloRecuperacao);
	public void update(ModuloRecuperacao moduloRecuperacao);
	public void delete(int id);
	public List<ModuloRecuperacao> findAll();
	public ModuloRecuperacao load(int id);

}
