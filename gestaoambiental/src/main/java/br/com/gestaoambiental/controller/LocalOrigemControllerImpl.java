package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.LocalOrigem;
import br.com.gestaoambiental.dao.LocalOrigemDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "localOrigemController")
@SessionScoped
public class LocalOrigemControllerImpl implements Controller {

	private LocalOrigem localOrigem;

	private List<LocalOrigem> localOrigemList;

	private LocalOrigemDAOImpl localOrigemDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaLocalOrigem";
	private static final String PAGINA_EDICAO = "editLocalOrigem";
	private static final String PAGINA_DETALHE = "detalheLocalOrigem";

	public LocalOrigemControllerImpl() {

		init();

	}

	public void init() {

		localOrigemDao = new LocalOrigemDAOImpl();

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

			localOrigemList = localOrigemDao
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

				if (localOrigem.getLoorId() == 0) {

					localOrigemDao.save(localOrigem);
					limparFormulario();
					MensagemUtil.infoMsg("localorigem_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					localOrigemDao.update(localOrigem);
					MensagemUtil.infoMsg("localorigem_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("localorigem_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			localOrigemDao.delete(localOrigem);

			MensagemUtil.infoMsg("localorigem_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("localorigem_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (localOrigem.getLoorDescricao().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("localorigem_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		localOrigem = new LocalOrigem();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public LocalOrigem getLocalOrigem() {
		return localOrigem;
	}

	public void setLocalOrigem(LocalOrigem localOrigem) {
		this.localOrigem = localOrigem;
	}

	public List<LocalOrigem> getLocalOrigemList() {
		return localOrigemList;
	}

	public void setLocalOrigemList(List<LocalOrigem> localOrigemList) {
		this.localOrigemList = localOrigemList;
	}

	public LocalOrigemDAOImpl getLocalOrigemDao() {
		return localOrigemDao;
	}

	public void setLocalOrigemDao(LocalOrigemDAOImpl localOrigemDao) {
		this.localOrigemDao = localOrigemDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
