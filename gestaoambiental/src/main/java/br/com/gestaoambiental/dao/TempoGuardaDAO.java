package br.com.gestaoambiental.dao;

import java.util.List;

import br.com.gestaoambiental.bean.TempoGuarda;

public interface TempoGuardaDAO {
	
	public void save(TempoGuarda tempoGuarda);
	public void update(TempoGuarda tempoGuarda);
	public void delete(int id);
	public List<TempoGuarda> findAll();
	public TempoGuarda load(int id);

}
