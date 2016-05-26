package br.com.gestaoambiental.bean;
// Generated 21/05/2016 14:21:33 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Empresa generated by hbm2java
 */
@Entity
@Table(name = "empresa", catalog = "gestao_ambiental")
public class Empresa implements java.io.Serializable {

	private Integer emprId;
	private String emprNome;
	private String emprGaragem;
	private String emprSetor;
	private List<Filial> filials = new ArrayList<Filial>();
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Empresa() {
	}

	public Empresa(String emprNome, String emprGaragem, String emprSetor, List<Filial> filials, Set<Usuario> usuarios) {
		this.emprNome = emprNome;
		this.emprGaragem = emprGaragem;
		this.emprSetor = emprSetor;
		this.filials = filials;
		this.usuarios = usuarios;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "empr_id", unique = true, nullable = false)
	public Integer getEmprId() {
		return this.emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	@Column(name = "empr_nome", length = 45)
	public String getEmprNome() {
		return this.emprNome;
	}

	public void setEmprNome(String emprNome) {
		this.emprNome = emprNome;
	}

	@Column(name = "empr_garagem", length = 45)
	public String getEmprGaragem() {
		return this.emprGaragem;
	}

	public void setEmprGaragem(String emprGaragem) {
		this.emprGaragem = emprGaragem;
	}

	@Column(name = "empr_setor", length = 45)
	public String getEmprSetor() {
		return this.emprSetor;
	}

	public void setEmprSetor(String emprSetor) {
		this.emprSetor = emprSetor;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Filial> getFilials() {
		return this.filials;
	}

	public void setFilials(List<Filial> filials) {
		this.filials = filials;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
