package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.Empresa;
import br.com.gestaoambiental.dao.EmpresaDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "empresaController")
@SessionScoped
public class EmpresaControllerImpl implements Controller {

	private Empresa empresa;

	private List<Empresa> empresaList;

	private EmpresaDAOImpl empresaDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaEmpresa";
	private static final String PAGINA_EDICAO = "editEmpresa";
	private static final String PAGINA_DETALHE = "detalheEmpresa";

	public EmpresaControllerImpl() {

		init();

	}

	public void init() {

		empresaDao = new EmpresaDAOImpl();

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

			empresaList = empresaDao
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

				if (empresa.getEmprId() == 0) {

					empresaDao.save(empresa);
					limparFormulario();
					MensagemUtil.infoMsg("empresa_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					empresaDao.update(empresa);
					MensagemUtil.infoMsg("empresa_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("empresa_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			empresaDao.delete(empresa);

			MensagemUtil.infoMsg("empresa_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("empresa_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (empresa.getEmprNome().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("empresa_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		empresa = new Empresa();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public EmpresaDAOImpl getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(EmpresaDAOImpl empresaDao) {
		this.empresaDao = empresaDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
