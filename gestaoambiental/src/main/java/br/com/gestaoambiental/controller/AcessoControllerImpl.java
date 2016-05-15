package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.Acesso;
import br.com.gestaoambiental.dao.AcessoDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "acessoController")
@SessionScoped
public class AcessoControllerImpl implements Controller {

	private Acesso acesso;

	private List<Acesso> acessoList;

	private AcessoDAOImpl acessoDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaAcesso";
	private static final String PAGINA_EDICAO = "editAcesso";
	private static final String PAGINA_DETALHE = "detalheAcesso";

	public AcessoControllerImpl() {

		init();

	}

	public void init() {

		acessoDao = new AcessoDAOImpl();

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

			acessoList = acessoDao
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

				if (acesso.getAcesId() == 0) {

					acessoDao.save(acesso);
					limparFormulario();
					MensagemUtil.infoMsg("acesso_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					acessoDao.update(acesso);
					MensagemUtil.infoMsg("acesso_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("acesso_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			acessoDao.delete(acesso);

			MensagemUtil.infoMsg("acesso_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("acesso_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (acesso.getAcesDescricao().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("acesso_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		acesso = new Acesso();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public List<Acesso> getAcessoList() {
		return acessoList;
	}

	public void setAcessoList(List<Acesso> acessoList) {
		this.acessoList = acessoList;
	}

	public AcessoDAOImpl getAcessoDao() {
		return acessoDao;
	}

	public void setAcessoDao(AcessoDAOImpl acessoDao) {
		this.acessoDao = acessoDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
