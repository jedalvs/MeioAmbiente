package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.ModuloRecuperacao;
import br.com.gestaoambiental.dao.ModuloRecuperacaoDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "moduloRecuperacaoController")
@SessionScoped
public class ModuloRecuperacaoControllerImpl implements Controller {

	private ModuloRecuperacao moduloRecuperacao;

	private List<ModuloRecuperacao> moduloRecuperacaoList;

	private ModuloRecuperacaoDAOImpl moduloRecuperacaoDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaModuloRecuperacao";
	private static final String PAGINA_EDICAO = "editModuloRecuperacao";
	private static final String PAGINA_DETALHE = "detalheModuloRecuperacao";

	public ModuloRecuperacaoControllerImpl() {

		init();

	}

	public void init() {

		moduloRecuperacaoDao = new ModuloRecuperacaoDAOImpl();

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

			moduloRecuperacaoList = moduloRecuperacaoDao
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

				if (moduloRecuperacao.getMoreId() == 0) {

					moduloRecuperacaoDao.save(moduloRecuperacao);
					limparFormulario();
					MensagemUtil.infoMsg("modulorecuperacao_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					moduloRecuperacaoDao.update(moduloRecuperacao);
					MensagemUtil.infoMsg("modulorecuperacao_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("modulorecuperacao_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			moduloRecuperacaoDao.delete(moduloRecuperacao);

			MensagemUtil.infoMsg("modulorecuperacao_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("modulorecuperacao_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (moduloRecuperacao.getMoreDescricao().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("modulorecuperacao_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		moduloRecuperacao = new ModuloRecuperacao();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public ModuloRecuperacao getModuloRecuperacao() {
		return moduloRecuperacao;
	}

	public void setModuloRecuperacao(ModuloRecuperacao moduloRecuperacao) {
		this.moduloRecuperacao = moduloRecuperacao;
	}

	public List<ModuloRecuperacao> getModuloRecuperacaoList() {
		return moduloRecuperacaoList;
	}

	public void setModuloRecuperacaoList(List<ModuloRecuperacao> moduloRecuperacaoList) {
		this.moduloRecuperacaoList = moduloRecuperacaoList;
	}

	public ModuloRecuperacaoDAOImpl getModuloRecuperacaoDao() {
		return moduloRecuperacaoDao;
	}

	public void setModuloRecuperacaoDao(ModuloRecuperacaoDAOImpl moduloRecuperacaoDao) {
		this.moduloRecuperacaoDao = moduloRecuperacaoDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
