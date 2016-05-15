package br.com.gestaoambiental.bean;

// Generated 23/04/2016 16:32:09 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TempoGuarda generated by hbm2java
 */
@Entity
@Table(name = "tempo_guarda", catalog = "gestao_ambiental")
public class TempoGuarda implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer teguId;
	private String teguDescricao;
	private Set<Documento> documentosForDocuIdTempoGuarda = new HashSet<Documento>(
			0);
	private Set<Documento> documentosForDocuIdTempoGuardaArquivoMorto = new HashSet<Documento>(
			0);

	public TempoGuarda() {
	}

	public TempoGuarda(String teguDescricao,
			Set<Documento> documentosForDocuIdTempoGuarda,
			Set<Documento> documentosForDocuIdTempoGuardaArquivoMorto) {
		this.teguDescricao = teguDescricao;
		this.documentosForDocuIdTempoGuarda = documentosForDocuIdTempoGuarda;
		this.documentosForDocuIdTempoGuardaArquivoMorto = documentosForDocuIdTempoGuardaArquivoMorto;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tegu_id", unique = true, nullable = false)
	public Integer getTeguId() {
		return this.teguId;
	}

	public void setTeguId(Integer teguId) {
		this.teguId = teguId;
	}

	@Column(name = "tegu_descricao", length = 50)
	public String getTeguDescricao() {
		return this.teguDescricao;
	}

	public void setTeguDescricao(String teguDescricao) {
		this.teguDescricao = teguDescricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tempoGuardaByDocuIdTempoGuarda")
	public Set<Documento> getDocumentosForDocuIdTempoGuarda() {
		return this.documentosForDocuIdTempoGuarda;
	}

	public void setDocumentosForDocuIdTempoGuarda(
			Set<Documento> documentosForDocuIdTempoGuarda) {
		this.documentosForDocuIdTempoGuarda = documentosForDocuIdTempoGuarda;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tempoGuardaByDocuIdTempoGuardaArquivoMorto")
	public Set<Documento> getDocumentosForDocuIdTempoGuardaArquivoMorto() {
		return this.documentosForDocuIdTempoGuardaArquivoMorto;
	}

	public void setDocumentosForDocuIdTempoGuardaArquivoMorto(
			Set<Documento> documentosForDocuIdTempoGuardaArquivoMorto) {
		this.documentosForDocuIdTempoGuardaArquivoMorto = documentosForDocuIdTempoGuardaArquivoMorto;
	}

}
