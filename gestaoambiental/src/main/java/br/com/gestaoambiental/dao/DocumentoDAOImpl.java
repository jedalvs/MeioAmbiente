package br.com.gestaoambiental.dao;

import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.connection.HibernateUtil;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

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
    
    public List<Documento> findAllVencimento(Date vencimento) throws Exception {
		session = HibernateUtil.getCurrentSession();
		criteria = session.createCriteria(Documento.class);
		criteria.add(Restrictions.eq("docuDataVencimento", vencimento));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
