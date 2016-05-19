package br.com.gestaoambiental.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gestaoambiental.bean.Usuario;
import br.com.gestaoambiental.dao.UsuarioDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioControllerImpl implements Controller {

	private Usuario usuario;

	private List<Usuario> usuarioList;

	private UsuarioDAOImpl usuarioDao;

	private String descricaoFiltro;

	private static final String PAGINA_LISTA = "listaUsuario";
	private static final String PAGINA_EDICAO = "editUsuario";
	private static final String PAGINA_DETALHE = "detalheUsuario";

	public UsuarioControllerImpl() {

		init();

	}

	public void init() {

		usuarioDao = new UsuarioDAOImpl();

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

			usuarioList = usuarioDao
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

				if (usuario.getUsuaId() == 0) {

					usuarioDao.save(usuario);
					limparFormulario();
					MensagemUtil.infoMsg("usuario_info_sucesso");
					carregarLista();

					return PAGINA_LISTA;

				} else {

					usuarioDao.update(usuario);
					MensagemUtil.infoMsg("usuario_info_sucesso");

					return PAGINA_DETALHE;

				}

			}

		} catch (Exception e) {

			MensagemUtil.errorMsg("usuario_erro_cadastro");

			e.printStackTrace();
		}

		return PAGINA_EDICAO;
	}

	public String remover() {

		try {

			usuarioDao.delete(usuario);

			MensagemUtil.infoMsg("usuario_info_sucesso_excluir");

		} catch (Exception e) {

			MensagemUtil.errorMsg("usuario_erro_excluir");

			e.printStackTrace();
		}

		limparFiltro();

		carregarLista();

		return PAGINA_LISTA;

	}

	public boolean validar() {

		boolean valido = true;

		if (usuario.getUsuaNome().equalsIgnoreCase("")) {

			MensagemUtil.warnMsg("usuario_warn_descricao");

			valido = false;

		}

		return valido;

	}

	public void limparFormulario() {

		usuario = new Usuario();

	}

	public void limparFiltro() {
		descricaoFiltro = "";

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public UsuarioDAOImpl getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAOImpl usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
