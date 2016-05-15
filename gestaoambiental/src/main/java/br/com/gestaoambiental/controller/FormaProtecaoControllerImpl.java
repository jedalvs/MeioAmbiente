package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.FormaProtecao;
import br.com.gestaoambiental.dao.FormaProtecaoDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "formaProtecaoController")
@SessionScoped
public class FormaProtecaoControllerImpl implements Controller {

	private FormaProtecao formaProtecao;

	private List<FormaProtecao> formaProtecaoList;

	private FormaProtecaoDAOImpl formaProtecaoDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaFormaProtecao";
	private static final String PAGINA_EDICAO = "editFormaProtecao";
	private static final String PAGINA_DETALHE = "detalheFormaProtecao";

	public FormaProtecaoControllerImpl() {

		init();

	}

	public void init() {

		formaProtecaoDao = new FormaProtecaoDAOImpl();

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

			formaProtecaoList = formaProtecaoDao
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

				if (formaProtecao.getFoprId() == 0) {

					formaProtecaoDao.save(formaProtecao);
					limparFormulario();
					MensagemUtil.infoMsg("formaprotecao_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					formaProtecaoDao.update(formaProtecao);
					MensagemUtil.infoMsg("formaprotecao_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("formaprotecao_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			formaProtecaoDao.delete(formaProtecao);

			MensagemUtil.infoMsg("formaprotecao_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("formaprotecao_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (formaProtecao.getFoprDescricao().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("formaprotecao_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		formaProtecao = new FormaProtecao();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public FormaProtecao getFormaProtecao() {
		return formaProtecao;
	}

	public void setFormaProtecao(FormaProtecao formaProtecao) {
		this.formaProtecao = formaProtecao;
	}

	public List<FormaProtecao> getFormaProtecaoList() {
		return formaProtecaoList;
	}

	public void setFormaProtecaoList(List<FormaProtecao> formaProtecaoList) {
		this.formaProtecaoList = formaProtecaoList;
	}

	public FormaProtecaoDAOImpl getFormaProtecaoDao() {
		return formaProtecaoDao;
	}

	public void setFormaProtecaoDao(FormaProtecaoDAOImpl formaProtecaoDao) {
		this.formaProtecaoDao = formaProtecaoDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
