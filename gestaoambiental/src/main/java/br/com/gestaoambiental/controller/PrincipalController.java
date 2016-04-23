package br.com.gestaoambiental.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PrincipalController {
	
    public PrincipalController() {
    	System.out.println("Teste");
    }
}
