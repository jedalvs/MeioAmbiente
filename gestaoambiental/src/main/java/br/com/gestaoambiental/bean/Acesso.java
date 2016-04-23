package br.com.gestaoambiental.bean;
// Generated 23/04/2016 16:32:09 by Hibernate Tools 4.3.1.Final

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
 * Acesso generated by hbm2java
 */
@Entity
@Table(name = "acesso", catalog = "gestao_ambiental")
public class Acesso implements java.io.Serializable {

	private Integer acesId;
	private String acesDescricao;
	private Set<Documento> documentos = new HashSet<Documento>(0);

	public Acesso() {
	}

	public Acesso(String acesDescricao, Set<Documento> documentos) {
		this.acesDescricao = acesDescricao;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "aces_id", unique = true, nullable = false)
	public Integer getAcesId() {
		return this.acesId;
	}

	public void setAcesId(Integer acesId) {
		this.acesId = acesId;
	}

	@Column(name = "aces_descricao", length = 50)
	public String getAcesDescricao() {
		return this.acesDescricao;
	}

	public void setAcesDescricao(String acesDescricao) {
		this.acesDescricao = acesDescricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acesso")
	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

}
