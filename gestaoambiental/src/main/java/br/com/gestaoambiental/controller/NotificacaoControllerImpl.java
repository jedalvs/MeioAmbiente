package br.com.gestaoambiental.controller;

import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.dao.DocumentoDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "notificacaoController")
@RequestScoped
public class NotificacaoControllerImpl {

    DocumentoDAOImpl documentoDAO = new DocumentoDAOImpl();
    List<Documento> lstDocumento;

    public NotificacaoControllerImpl() {
        init();
    }

    private void init() {
        try {
            lstDocumento = documentoDAO.getNotificacao();
        } catch (Exception ex) {
            Logger.getLogger(NotificacaoControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Documento> getLstDocumento() {
        return lstDocumento;
    }

    public void setLstDocumento(List<Documento> lstDocumento) {
        this.lstDocumento = lstDocumento;
    }
}
