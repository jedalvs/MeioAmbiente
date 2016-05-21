package br.com.gestaoambiental.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.gestaoambiental.bean.Empresa;
import br.com.gestaoambiental.bean.Filial;
import br.com.gestaoambiental.dao.EmpresaDAOImpl;
import br.com.gestaoambiental.dao.FilialDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "filialController")
@SessionScoped
public class FilialControllerImpl implements Controller {

	private Filial filial;
	private List<SelectItem> cbEmpresa;
	private Integer idEmpresaSelecionado;
	private List<Filial> filialList;
	private EmpresaDAOImpl empresaDao;
	private FilialDAOImpl filialDao;
	private String descricaoFiltro;
	
	private static final String PAGINA_LISTA = "listaFilial";
	private static final String PAGINA_EDICAO = "editFilial";
	private static final String PAGINA_DETALHE = "detalheFilial";

	public FilialControllerImpl() {

		init();

	}

	public void init() {
		empresaDao = new EmpresaDAOImpl();
		filialDao = new FilialDAOImpl();

	}

	public String navLista() {
		initCbEmpresa();
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

			filialList = filialDao
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

				if (filial.getFiliId() == 0) {
					Empresa empresa = new Empresa();
					empresa.setEmprId(idEmpresaSelecionado);
					filial.setEmpresa(empresa);
					filialDao.save(filial);
					limparFormulario();
					MensagemUtil.infoMsg("filial_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					filialDao.update(filial);
					MensagemUtil.infoMsg("filial_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("filial_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			filialDao.delete(filial);

			MensagemUtil.infoMsg("filial_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("filial_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (filial.getFiliNome().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("filial_warn_descricao");

			valido = false;

		}

		return valido;

	}
	
	public void initCbEmpresa() {
		try {
			this.cbEmpresa = new ArrayList<SelectItem>();
			List<Empresa> empresas = empresaDao.findAll();
			
			for (Empresa empresa : empresas) {
				SelectItem s = new SelectItem();
				s.setLabel(empresa.getEmprNome());
				s.setValue(empresa.getEmprId());
				this.cbEmpresa.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limparFormulario() {

		filial = new Filial();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Filial> getFilialList() {
		return filialList;
	}

	public void setFilialList(List<Filial> filialList) {
		this.filialList = filialList;
	}

	public FilialDAOImpl getFilialDao() {
		return filialDao;
	}

	public void setFilialDao(FilialDAOImpl filialDao) {
		this.filialDao = filialDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public List<SelectItem> getCbEmpresa() {
		return cbEmpresa;
	}

	public void setCbEmpresa(List<SelectItem> cbEmpresa) {
		this.cbEmpresa = cbEmpresa;
	}

	public Integer getIdEmpresaSelecionado() {
		return idEmpresaSelecionado;
	}

	public void setIdEmpresaSelecionado(Integer idEmpresaSelecionado) {
		this.idEmpresaSelecionado = idEmpresaSelecionado;
	}
	
	

}
