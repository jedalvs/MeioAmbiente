package br.com.gestaoambiental.bean;
// Generated 15/05/2016 16:28:26 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ModuloRecuperacao generated by hbm2java
 */
@Entity
@Table(name = "modulo_recuperacao", catalog = "gestao_ambiental")
public class ModuloRecuperacao implements java.io.Serializable {

	private Integer moreId;
	private String moreDescricao;
	private Set<Documento> documentos = new HashSet<Documento>(0);

	public ModuloRecuperacao() {
	}

	public ModuloRecuperacao(String moreDescricao, Set<Documento> documentos) {
		this.moreDescricao = moreDescricao;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "more_id", unique = true, nullable = false)
	public Integer getMoreId() {
		return this.moreId;
	}

	public void setMoreId(Integer moreId) {
		this.moreId = moreId;
	}

	@Column(name = "more_descricao", length = 50)
	public String getMoreDescricao() {
		return this.moreDescricao;
	}

	public void setMoreDescricao(String moreDescricao) {
		this.moreDescricao = moreDescricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "moduloRecuperacao")
	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

}
