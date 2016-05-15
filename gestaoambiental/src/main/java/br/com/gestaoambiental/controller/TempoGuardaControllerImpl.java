package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.TempoGuarda;
import br.com.gestaoambiental.dao.TempoGuardaDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "tempoGuardaController")
@SessionScoped
public class TempoGuardaControllerImpl implements Controller {

	private TempoGuarda tempoGuarda;

	private List<TempoGuarda> tempoGuardaList;

	private TempoGuardaDAOImpl tempoGuardaDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaTempoGuarda";
	private static final String PAGINA_EDICAO = "editTempoGuarda";
	private static final String PAGINA_DETALHE = "detalheTempoGuarda";

	public TempoGuardaControllerImpl() {

		init();

	}

	public void init() {

		tempoGuardaDao = new TempoGuardaDAOImpl();

	}

	public String navLista() {

		limparFiltro();
		carregarLista();

		return PAGINA_LISTA;

	}

	public String navAdd() {

		limparFormulario();

		return PAGINA_EDICAO;

	}

	public String navDetalhe() {

		return PAGINA_DETALHE;
	}

	public String navEdit() {

		obterObjeto();

		return PAGINA_EDICAO;

	}

	public void carregarLista() {

		try {

			tempoGuardaList = tempoGuardaDao
					.findByDescricao(descricaoFiltro);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void obterObjeto() {

	}

	public String salvar() {

		try {

			if (validar()) {

				if (tempoGuarda.getTeguId() == 0) {

					tempoGuardaDao.save(tempoGuarda);
					limparFormulario();
					MensagemUtil.infoMsg("tempoguarda_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					tempoGuardaDao.update(tempoGuarda);
					MensagemUtil.infoMsg("tempoguarda_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("tempoguarda_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			tempoGuardaDao.delete(tempoGuarda);

			MensagemUtil.infoMsg("tempoguarda_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("tempoguarda_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (tempoGuarda.getTeguDescricao().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("tempoguarda_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		tempoGuarda = new TempoGuarda();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public TempoGuarda getTempoGuarda() {
		return tempoGuarda;
	}

	public void setTempoGuarda(TempoGuarda tempoGuarda) {
		this.tempoGuarda = tempoGuarda;
	}

	public List<TempoGuarda> getTempoGuardaList() {
		return tempoGuardaList;
	}

	public void setTempoGuardaList(List<TempoGuarda> tempoGuardaList) {
		this.tempoGuardaList = tempoGuardaList;
	}

	public TempoGuardaDAOImpl getTempoGuardaDao() {
		return tempoGuardaDao;
	}

	public void setTempoGuardaDao(TempoGuardaDAOImpl tempoGuardaDao) {
		this.tempoGuardaDao = tempoGuardaDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
