package br.com.gestaoambiental.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.gestaoambiental.bean.Acesso;
import br.com.gestaoambiental.bean.Anexo;
import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.bean.Filial;
import br.com.gestaoambiental.bean.LocalOrigem;
import br.com.gestaoambiental.bean.Usuario;
import br.com.gestaoambiental.dao.AcessoDAOImpl;
import br.com.gestaoambiental.dao.AnexoDaoImpl;
import br.com.gestaoambiental.dao.DocumentoDAOImpl;
import br.com.gestaoambiental.dao.EmpresaDAOImpl;
import br.com.gestaoambiental.dao.FilialDAOImpl;
import br.com.gestaoambiental.dao.LocalOrigemDAOImpl;
import br.com.gestaoambiental.dao.UsuarioDAOImpl;
import br.com.gestaoambiental.util.MensagemUtil;

@ManagedBean(name = "documentoController")
@ViewScoped
public class DocumentoControllerImpl implements Controller {

    private StreamedContent arquivoDownload;
    private static final String FILE_PATH = "C:\\sga\\";
    private List<Anexo> arquivosAnexos;
    private Anexo anexoSelecionado;
    private Documento documento;
    private Documento documentoSelecionado;
    private List<Documento> documentos;
    private Acesso acesso;
    private LocalOrigem localOrigem;
    private LocalOrigem localGuarda;
    private Set<Anexo> anexos;
    private DocumentoDAOImpl documentoDao;
    private AcessoDAOImpl acessoDao;
    private LocalOrigemDAOImpl localOrigemDao;
    private AnexoDaoImpl anexoDao;
    private UsuarioDAOImpl usuarioDAO;
    private EmpresaDAOImpl empresaDAO;
    private FilialDAOImpl filialDAO;
    private List<SelectItem> cbAcesso;
    private List<SelectItem> cbFormaProtecao;
    private List<SelectItem> cbLocalOrigem;
    private List<SelectItem> cbLocalGuarda;
    private List<SelectItem> cbModuloRecuperacao;
    private List<SelectItem> cbTempoGuarda;
    private List<SelectItem> cbTempoGuardaArquivoMorto;
    private List<SelectItem> cbFilial;
    private Integer idAcessoSelecionado;
    private String idFormaProtecaoSelecionado;
    private Integer idLocalOrigemSelecionado;
    private Integer idLocalGuardaSelecionado;
    private String idModuloRecuperacaoSelecionado;
    private String idTempoGuardaSelecionado;
    private String idTempoGuardaArquivoMortoSelecionado;
    private List<Filial> filiais;
   
    private static final String PAGINA_LISTA = "listaDocumento";

    public DocumentoControllerImpl() {
        init();
    }
    
    public String notificaoDocumento(Documento doc) {
        init();

        limparFormulario();
        carregarLista();

        setDocumentoSelecionado(doc);
        obterDocumento();

        String url = "documento/lista.xhtml?faces-redirect=true";

        return url;
    }

   
    public void init() {
        documentoDao = new DocumentoDAOImpl();
        acessoDao = new AcessoDAOImpl();
        localOrigemDao = new LocalOrigemDAOImpl();
        anexoDao = new AnexoDaoImpl();
        usuarioDAO = new UsuarioDAOImpl();
        empresaDAO = new EmpresaDAOImpl();
        filialDAO = new FilialDAOImpl();
        anexos = new HashSet<Anexo>();

        arquivoDownload = new DefaultStreamedContent();
        anexoSelecionado = new Anexo();
        
        documento = new Documento();
        documentoSelecionado = new Documento();
        anexos = new HashSet<Anexo>();
        arquivosAnexos = new ArrayList<Anexo>();
        
        limparFormulario();
        carregarLista();
        initCbAcesso();
        initCbLocalGuarda();
        initCbLocalOrigem();
        
//        setDocumentoSelecionado(doc);
//        obterDocumento();

    }

    private Usuario buscarUsuario() {
        Usuario usua = null;
        SecurityContext contextSecurity = SecurityContextHolder.getContext();
        if (contextSecurity instanceof SecurityContext) {
            Authentication authentication = contextSecurity.getAuthentication();
            if (authentication instanceof Authentication) {
                String login = authentication.getName();
                try {
                    usua = usuarioDAO.load(login);
                    filiais = usua.getEmpresa().getFilials();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return usua;
    }

    public String navLista() {
        documento = new Documento();
        documentoSelecionado = new Documento();
        anexos = new HashSet<Anexo>();
        arquivosAnexos = new ArrayList<Anexo>();

        initCbAcesso();
        initCbLocalGuarda();
        initCbLocalOrigem();
        //initCbFilial();
        limparFormulario();
        carregarLista();
        documento.setUsuario(buscarUsuario());
        return PAGINA_LISTA;
    }

    public String navAdd() {
        documento = new Documento();
        anexos = new HashSet<Anexo>();
        limparFormulario();
        arquivosAnexos = new ArrayList<Anexo>();
        return PAGINA_LISTA;
    }

    public String navDetalhe() {
        // TODO Auto-generated method stub
        return null;
    }

    public String navEdit() {
        // TODO Auto-generated method stub
        return null;
    }

    public void carregarLista() {
        try {
            documentos = documentoDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obterDocumento() {
        documento = documentoSelecionado;
        idAcessoSelecionado = documentoSelecionado.getAcesso().getAcesId();
        idLocalOrigemSelecionado = documentoSelecionado.getLocalOrigemByDocuIdLocalOrigem().getLoorId();
        idLocalGuardaSelecionado = documentoSelecionado.getLocalOrigemByDocuLocalGuarda().getLoorId();
        idFormaProtecaoSelecionado = documentoSelecionado.getDocuFormaProtecao();
        idTempoGuardaSelecionado = documentoSelecionado.getDocuTempoGuarda();
        idTempoGuardaArquivoMortoSelecionado = documentoSelecionado.getDocuTempoGuardaArquivoMorto();
        idModuloRecuperacaoSelecionado = documentoSelecionado.getDocuModRecuperacao();

        try {
            arquivosAnexos = anexoDao.findByDocumento(documento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String salvar() {
        try {
            if (validar()) {
                localOrigem = localOrigemDao.load(idLocalOrigemSelecionado);
                localGuarda = localOrigemDao.load(idLocalGuardaSelecionado);
                acesso = acessoDao.load(idAcessoSelecionado);
                documento.setDocuFormaProtecao(idFormaProtecaoSelecionado);
                documento.setDocuTempoGuarda(idTempoGuardaSelecionado);
                documento.setDocuTempoGuardaArquivoMorto(idTempoGuardaArquivoMortoSelecionado);
                documento.setDocuModRecuperacao(idModuloRecuperacaoSelecionado);
                documento.setLocalOrigemByDocuIdLocalOrigem(localOrigem);
                documento.setLocalOrigemByDocuLocalGuarda(localGuarda);
                documento.setAcesso(acesso);
                Set<Anexo> listaAnexo = new HashSet<Anexo>();

                if (anexos.size() > 0) {
                    for (Anexo a : anexos) {
                        String folder = documento.getDocuIdentificacao().replace(" ", "");
                        a.setDocumento(documento);
                        a.setAnexCaminho(a.getAnexCaminho().replace("FLAG", folder));
                        listaAnexo.add(a);
                        new File(FILE_PATH + folder).mkdir();
                        copyFile(a.getAnexCaminho(), a.getArquivo());

                    }
                    documento.setAnexos(listaAnexo);
                }

                if (documento.getDocuId() == 0) {
                    documentoDao.save(documento);
                    limparFormulario();
                } else {
                    documentoDao.update(documento);
                    arquivosAnexos = anexoDao.findByDocumento(documento);

                }
                MensagemUtil.infoMsg("documento_info_sucesso");
            }
        } catch (Exception e) {
            MensagemUtil.errorMsg("documento_erro_cadastro");
            e.printStackTrace();
        } finally {
            anexos = new HashSet<Anexo>();
        }
        carregarLista();
        return PAGINA_LISTA;
    }

    public String remover() {
        try {
            documentoDao.delete(documento);
            arquivosAnexos = new ArrayList<Anexo>();
            MensagemUtil.infoMsg("documento_info_sucesso_excluir");
        } catch (Exception e) {
            MensagemUtil.errorMsg("documento_erro_cadastro");
            e.printStackTrace();
        }
        limparFormulario();
        carregarLista();
        return PAGINA_LISTA;
    }

    public boolean validar() {
        boolean valido = true;
        if (documento.getDocuDataManutencao() == null) {
            MensagemUtil.warnMsg("documento_warn_datamanutencao");
            valido = false;
        }

        if (documento.getDocuIdentificacao().equalsIgnoreCase("")) {
            MensagemUtil.warnMsg("documento_warn_identificacaodocumento");
            valido = false;
        }

        if (idFormaProtecaoSelecionado == "0") {
            MensagemUtil.warnMsg("documento_warn_formaprotecao");
            valido = false;
        }

        if (idLocalOrigemSelecionado == 0) {
            MensagemUtil.warnMsg("documento_warn_localorigem");
            valido = false;
        }

        if (idLocalGuardaSelecionado == 0) {
            MensagemUtil.warnMsg("documento_warn_localguarda");
            valido = false;
        }

        if (idAcessoSelecionado == 0) {
            MensagemUtil.warnMsg("documento_warn_acesso");
            valido = false;
        }

        if (idModuloRecuperacaoSelecionado == "0") {
            MensagemUtil.warnMsg("documento_warn_metodorecuperacao");
            valido = false;
        }

        if (idTempoGuardaSelecionado == "0") {
            MensagemUtil.warnMsg("documento_warn_tempoguardasetor");
            valido = false;
        }

        if (idTempoGuardaArquivoMortoSelecionado == "0") {
            MensagemUtil.warnMsg("documento_warn_tempoguardaarquivomorto");
            valido = false;
        }
        return valido;
    }

    public void limparFormulario() {
        documento = new Documento();
        documento.setUsuario(buscarUsuario());
        idAcessoSelecionado = 0;
        idFormaProtecaoSelecionado = "";
        idLocalOrigemSelecionado = 0;
        idLocalGuardaSelecionado = 0;
        idModuloRecuperacaoSelecionado = "";
        idTempoGuardaSelecionado = "";
        idTempoGuardaArquivoMortoSelecionado = "";
        arquivosAnexos = new ArrayList<Anexo>();
    }

    public void limparFiltro() {
        // TODO Auto-generated method stub
    }

    public void initCbAcesso() {
        try {
            this.cbAcesso = new ArrayList<SelectItem>();
            List<Acesso> acessos = acessoDao.findAll();
            for (Acesso acesso : acessos) {
                SelectItem s = new SelectItem();
                s.setLabel(acesso.getAcesDescricao());
                s.setValue(acesso.getAcesId());
                this.cbAcesso.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCbLocalOrigem() {
        try {
            this.cbLocalOrigem = new ArrayList<SelectItem>();
            List<LocalOrigem> localOrigemList = localOrigemDao.findAll();
            for (LocalOrigem localOrigem : localOrigemList) {
                SelectItem s = new SelectItem();
                s.setLabel(localOrigem.getLoorDescricao());
                s.setValue(localOrigem.getLoorId());
                this.cbLocalOrigem.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCbLocalGuarda() {
        try {
            this.cbLocalGuarda = new ArrayList<SelectItem>();
            List<LocalOrigem> localGuardaList = localOrigemDao.findAll();
            for (LocalOrigem localGuarda : localGuardaList) {
                SelectItem s = new SelectItem();
                s.setLabel(localGuarda.getLoorDescricao());
                s.setValue(localGuarda.getLoorId());
                this.cbLocalGuarda.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//	public void initCbFilial() {
//		try {
//			this.cbFilial = new ArrayList<SelectItem>();
//			for (Filial filial : filiais) {
//				SelectItem s = new SelectItem();
//				s.setLabel(filial.getFiliNome());
//				s.setValue(filial.getFiliId());
//				this.cbFilial.add(s);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
    public void upload(FileUploadEvent event) {
        try {
            String nomeArquivo =new String(event.getFile()
                    .getFileName()
                    .getBytes(Charset.defaultCharset()),"UTF-8");
            Anexo anexo = new Anexo();
            anexo.setArquivo(event.getFile().getInputstream());
            anexo.setAnexCaminho(DocumentoControllerImpl.FILE_PATH + "FLAG\\" + nomeArquivo);
            anexo.setNomeArquivo(nomeArquivo);
            anexos.add(anexo);
            arquivosAnexos.add(anexo);
            MensagemUtil.infoMsg("arquivo_upload_sucesso");
        } catch (IOException e) {
            MensagemUtil.errorMsg("arquivo_upload_erro");
            e.printStackTrace();
        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dateToString(Date data) {
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String result;
        if (data != null) {
            result = out.format(data);
        } else {
            result = "";
        }
        return result;
    }
    
    public void deleteArquivo(Anexo anexo) throws Exception{    	
    	String filepath = anexo.getAnexCaminho().replace("\\", "\\\\");    	
    	File file = new File(filepath);
    	
		if(file.delete()){
			anexoDao.delete(anexo);
	    	arquivosAnexos.clear();
	    	arquivosAnexos = anexoDao.findByDocumento(documentoSelecionado);
		}
    }

    public void baixarArquivo(String caminho, String nomeArquivo) {
        try {
            String filepath = caminho.replace("\\", "\\\\");
            InputStream stream = new FileInputStream(new File(filepath));
            String extencao = buscarExtencao(filepath);
            if (extencao.equalsIgnoreCase("pdf")) {
                arquivoDownload = new DefaultStreamedContent(stream, "application/pdf", nomeArquivo);
            }
            if (extencao.equalsIgnoreCase("doc")) {
                arquivoDownload = new DefaultStreamedContent(stream, "application/msword", nomeArquivo);
            }

            if (extencao.equalsIgnoreCase("docx")) {
                arquivoDownload = new DefaultStreamedContent(stream,
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                        nomeArquivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	
		}
    }

    public String buscarExtencao(String path) {
        StringTokenizer st = new StringTokenizer(path, ".");
        List<String> tokens = new ArrayList<String>();

        // pega cada token
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        String extencao = tokens.get(1).trim();
        return extencao;
    }

    public StreamedContent getArquivoDownload() {
        return arquivoDownload;
    }

    public void setArquivoDownload(StreamedContent arquivoDownload) {
        this.arquivoDownload = arquivoDownload;
    }

    public List<Anexo> getArquivosAnexos() {
        return arquivosAnexos;
    }

    public void setArquivosAnexos(List<Anexo> arquivosAnexos) {
        this.arquivosAnexos = arquivosAnexos;
    }

    public Anexo getAnexoSelecionado() {
        return anexoSelecionado;
    }

    public void setAnexoSelecionado(Anexo anexoSelecionado) {
        this.anexoSelecionado = anexoSelecionado;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumentoSelecionado() {
        return documentoSelecionado;
    }

    public void setDocumentoSelecionado(Documento documentoSelecionado) {
        this.documentoSelecionado = documentoSelecionado;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public LocalOrigem getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(LocalOrigem localOrigem) {
        this.localOrigem = localOrigem;
    }

    public LocalOrigem getLocalGuarda() {
        return localGuarda;
    }

    public void setLocalGuarda(LocalOrigem localGuarda) {
        this.localGuarda = localGuarda;
    }

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
    }

    public DocumentoDAOImpl getDocumentoDao() {
        return documentoDao;
    }

    public void setDocumentoDao(DocumentoDAOImpl documentoDao) {
        this.documentoDao = documentoDao;
    }

    public AcessoDAOImpl getAcessoDao() {
        return acessoDao;
    }

    public void setAcessoDao(AcessoDAOImpl acessoDao) {
        this.acessoDao = acessoDao;
    }

    public LocalOrigemDAOImpl getLocalOrigemDao() {
        return localOrigemDao;
    }

    public void setLocalOrigemDao(LocalOrigemDAOImpl localOrigemDao) {
        this.localOrigemDao = localOrigemDao;
    }

    public AnexoDaoImpl getAnexoDao() {
        return anexoDao;
    }

    public void setAnexoDao(AnexoDaoImpl anexoDao) {
        this.anexoDao = anexoDao;
    }

    public List<SelectItem> getCbAcesso() {
        return cbAcesso;
    }

    public void setCbAcesso(List<SelectItem> cbAcesso) {
        this.cbAcesso = cbAcesso;
    }

    public List<SelectItem> getCbFormaProtecao() {
        return cbFormaProtecao;
    }

    public void setCbFormaProtecao(List<SelectItem> cbFormaProtecao) {
        this.cbFormaProtecao = cbFormaProtecao;
    }

    public List<SelectItem> getCbLocalOrigem() {
        return cbLocalOrigem;
    }

    public void setCbLocalOrigem(List<SelectItem> cbLocalOrigem) {
        this.cbLocalOrigem = cbLocalOrigem;
    }

    public List<SelectItem> getCbLocalGuarda() {
        return cbLocalGuarda;
    }

    public void setCbLocalGuarda(List<SelectItem> cbLocalGuarda) {
        this.cbLocalGuarda = cbLocalGuarda;
    }

    public List<SelectItem> getCbModuloRecuperacao() {
        return cbModuloRecuperacao;
    }

    public void setCbModuloRecuperacao(List<SelectItem> cbModuloRecuperacao) {
        this.cbModuloRecuperacao = cbModuloRecuperacao;
    }

    public List<SelectItem> getCbTempoGuarda() {
        return cbTempoGuarda;
    }

    public void setCbTempoGuarda(List<SelectItem> cbTempoGuarda) {
        this.cbTempoGuarda = cbTempoGuarda;
    }

    public List<SelectItem> getCbTempoGuardaArquivoMorto() {
        return cbTempoGuardaArquivoMorto;
    }

    public void setCbTempoGuardaArquivoMorto(
            List<SelectItem> cbTempoGuardaArquivoMorto) {
        this.cbTempoGuardaArquivoMorto = cbTempoGuardaArquivoMorto;
    }

    public Integer getIdAcessoSelecionado() {
        return idAcessoSelecionado;
    }

    public void setIdAcessoSelecionado(Integer idAcessoSelecionado) {
        this.idAcessoSelecionado = idAcessoSelecionado;
    }

    public String getIdFormaProtecaoSelecionado() {
        return idFormaProtecaoSelecionado;
    }

    public void setIdFormaProtecaoSelecionado(String idFormaProtecaoSelecionado) {
        this.idFormaProtecaoSelecionado = idFormaProtecaoSelecionado;
    }

    public Integer getIdLocalOrigemSelecionado() {
        return idLocalOrigemSelecionado;
    }

    public void setIdLocalOrigemSelecionado(Integer idLocalOrigemSelecionado) {
        this.idLocalOrigemSelecionado = idLocalOrigemSelecionado;
    }

    public Integer getIdLocalGuardaSelecionado() {
        return idLocalGuardaSelecionado;
    }

    public void setIdLocalGuardaSelecionado(Integer idLocalGuardaSelecionado) {
        this.idLocalGuardaSelecionado = idLocalGuardaSelecionado;
    }

    public String getIdModuloRecuperacaoSelecionado() {
        return idModuloRecuperacaoSelecionado;
    }

    public void setIdModuloRecuperacaoSelecionado(
            String idModuloRecuperacaoSelecionado) {
        this.idModuloRecuperacaoSelecionado = idModuloRecuperacaoSelecionado;
    }

    public String getIdTempoGuardaSelecionado() {
        return idTempoGuardaSelecionado;
    }

    public void setIdTempoGuardaSelecionado(String idTempoGuardaSelecionado) {
        this.idTempoGuardaSelecionado = idTempoGuardaSelecionado;
    }

    public String getIdTempoGuardaArquivoMortoSelecionado() {
        return idTempoGuardaArquivoMortoSelecionado;
    }

    public void setIdTempoGuardaArquivoMortoSelecionado(
            String idTempoGuardaArquivoMortoSelecionado) {
        this.idTempoGuardaArquivoMortoSelecionado = idTempoGuardaArquivoMortoSelecionado;
    }

    public void obterObjeto() {
        // TODO Auto-generated method stub

    }
}
