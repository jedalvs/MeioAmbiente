package br.com.gestaoambiental.controller;

public interface Controller {
	
	public void init();

	//Navegação
	public String navLista();
	public String navAdd();
	public String navDetalhe();
	public String navEdit();
	
	//Busca
	public void carregarLista();
	public void obterObjeto();
	
	//Escrita
	public String salvar();
	public String remover();
	
	//Validação
	public boolean validar();
	
	//Lógica de tela	
	public void limparFormulario();
	public void limparFiltro();
	
	
	

}
