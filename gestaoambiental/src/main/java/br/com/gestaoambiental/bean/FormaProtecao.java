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
 * FormaProtecao generated by hbm2java
 */
@Entity
@Table(name = "forma_protecao", catalog = "gestao_ambiental")
public class FormaProtecao implements java.io.Serializable {

	private Integer foprId;
	private String foprDescricao;
	private Set<Documento> documentos = new HashSet<Documento>(0);

	public FormaProtecao() {
	}

	public FormaProtecao(String foprDescricao, Set<Documento> documentos) {
		this.foprDescricao = foprDescricao;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "fopr_id", unique = true, nullable = false)
	public Integer getFoprId() {
		return this.foprId;
	}

	public void setFoprId(Integer foprId) {
		this.foprId = foprId;
	}

	@Column(name = "fopr_descricao", length = 50)
	public String getFoprDescricao() {
		return this.foprDescricao;
	}

	public void setFoprDescricao(String foprDescricao) {
		this.foprDescricao = foprDescricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formaProtecao")
	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

}
