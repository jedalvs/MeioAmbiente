package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.Empresa;

public interface EmpresaDAO {
	
	public void save(Empresa empresa);
	public void update(Empresa empres);
	public void delete(int id);
	public List<Empresa> findAll();
	public Empresa load(int id);

}
