package br.com.gestaoambiental.dao;

import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.connection.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;

public class DocumentoDAOImpl extends DaoImpl<Documento, Integer> {

    public DocumentoDAOImpl() {
        super(Documento.class);
    }

    public List<Documento> getNotificacao()
            throws Exception {
        session = HibernateUtil.getCurrentSession();
        String sql = ""
                + " select"
                + "	* from documento"
                + " where"
                + " :data >=  DATE_ADD(docu_data_vencimento, interval (-1) * docu_qtd_dias_renovacao day);";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Documento.class);
        query.setParameter("data", new Date());
        return query.list();
    }

}
