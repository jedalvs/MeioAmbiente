package br.com.gestaoambiental.controller;

public interface Controller {
	
	public void init();

	//Navega��o
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
	
	//Valida��o
	public boolean validar();
	
	//L�gica de tela	
	public void limparFormulario();
	public void limparFiltro();
	
	
	

}
