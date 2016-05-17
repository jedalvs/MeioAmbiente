package br.com.gestaoambiental.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.gestaoambiental.bean.Usuario;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginControllerImpl implements Serializable {
 
    private Usuario usuario;
 
    public String login() throws ServletException, IOException {
        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher expedir = ((ServletRequest) contexto.getRequest()).getRequestDispatcher("/j_spring_security_check");
        expedir.forward((ServletRequest) contexto.getRequest(), (ServletResponse) contexto.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
       
        
        return null;
     }     
}
