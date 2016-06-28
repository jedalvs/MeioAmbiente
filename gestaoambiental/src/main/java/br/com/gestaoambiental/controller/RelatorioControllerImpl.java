package br.com.gestaoambiental.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.dao.DocumentoDAOImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "relatorioController")
@SessionScoped
public class RelatorioControllerImpl implements RelController {

	private Date dataVencimento;
	private Date dataVencimentoFim;

	
	private static final String PAGINA_REL_TODOS = "relTodosDocumentos";
	private static final String PAGINA_REL_VENCIMENTO = "relDocumentosVencidos";
	private DocumentoDAOImpl documentoDao;

	public RelatorioControllerImpl() {
		init();
	}
	
	public void init() {
		 documentoDao = new DocumentoDAOImpl();		
	}
	
	public String navListaTodos() {
		return PAGINA_REL_TODOS;
	}
	
	public String navListaVencimento() {
		return PAGINA_REL_VENCIMENTO;
	}
	
	public String gerarRelatorio() {
		  FacesContext facesContext = FacesContext.getCurrentInstance(); 
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse(); 
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/WEB-INF/relatorios/relTodosDocumentos.jasper"); 
	        response.setContentType("application/pdf"); 
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	 
	        try { 
	            ServletOutputStream servletOutputStream = response.getOutputStream(); 
	            List<Documento> documentos =  documentoDao.findAll();        	
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, null, new JRBeanCollectionDataSource(documentos));	           
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {			
				e.printStackTrace();
			} finally{
	            facesContext.responseComplete();
	        }
		return null;
	}


	public String gerarRelatorioParametros() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse(); 
        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/WEB-INF/relatorios/relTodosDocumentos.jasper"); 
        response.setContentType("application/pdf"); 
        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
 
        try {    
            ServletOutputStream servletOutputStream = response.getOutputStream(); 
            HashMap<String, Object> param = new HashMap<String, Object>();			
        	param.put("vencimento", dataVencimento);         	
        	List<Documento> documentos =  documentoDao.findAllVencimento(dataVencimento, dataVencimentoFim);        	
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, null, new JRBeanCollectionDataSource(documentos)); 
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {			
			e.printStackTrace();
		} finally{
            facesContext.responseComplete();
        }
		return null;
	}


	
	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimentoFim() {
		return dataVencimentoFim;
	}

	public void setDataVencimentoFim(Date dataVencimentoFim) {
		this.dataVencimentoFim = dataVencimentoFim;
	}
	
	
}
