package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.Usuario;

public interface UsuarioDAO {
	
	public void save(Usuario usuario);
	public void update(Usuario usuario);
	public void delete(int id);
	public List<Usuario> findAll();
	public Usuario load(int id);
}
