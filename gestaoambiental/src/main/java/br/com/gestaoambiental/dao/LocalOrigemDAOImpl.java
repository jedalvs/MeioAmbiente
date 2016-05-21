package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.LocalOrigem;
import br.com.gestaoambiental.connection.HibernateUtil;

public class LocalOrigemDAOImpl extends DaoImpl<LocalOrigem, Integer> {

	public LocalOrigemDAOImpl() {
		super(LocalOrigem.class);
	}

	@SuppressWarnings("unchecked")
	public List<LocalOrigem> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new LocalOrigem().getClass());

		criteria.add(Restrictions.like("loorDescricao", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<LocalOrigem> locais = criteria.list();

		return locais;

	}

}
