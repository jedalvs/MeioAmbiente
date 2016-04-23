package br.com.gestaoambiental.bean;
// Generated 23/04/2016 16:32:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Anexo generated by hbm2java
 */
@Entity
@Table(name = "anexo", catalog = "gestao_ambiental")
public class Anexo implements java.io.Serializable {

	private Integer anexId;
	private Documento documento;
	private String anexCaminho;

	public Anexo() {
	}

	public Anexo(Documento documento, String anexCaminho) {
		this.documento = documento;
		this.anexCaminho = anexCaminho;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "anex_id", unique = true, nullable = false)
	public Integer getAnexId() {
		return this.anexId;
	}

	public void setAnexId(Integer anexId) {
		this.anexId = anexId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anex_id_documento")
	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Column(name = "anex_caminho", length = 100)
	public String getAnexCaminho() {
		return this.anexCaminho;
	}

	public void setAnexCaminho(String anexCaminho) {
		this.anexCaminho = anexCaminho;
	}

}
