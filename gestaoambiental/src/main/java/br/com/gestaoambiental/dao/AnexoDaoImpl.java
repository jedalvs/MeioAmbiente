package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.Anexo;
import br.com.gestaoambiental.bean.Documento;
import br.com.gestaoambiental.connection.HibernateUtil;

public class AnexoDaoImpl extends DaoImpl<Anexo, Integer> {

	public AnexoDaoImpl() {
		super(Anexo.class);
	}

	@SuppressWarnings("unchecked")
	public List<Anexo> findByDocumento(Documento documento) throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new Anexo().getClass());

		criteria.add(Restrictions.eq("documento", documento));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Anexo> acessos = criteria.list();

		return acessos;

	}

}
