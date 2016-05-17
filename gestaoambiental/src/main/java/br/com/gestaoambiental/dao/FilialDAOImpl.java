package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.Filial;
import br.com.gestaoambiental.connection.HibernateUtil;

public class FilialDAOImpl extends DaoImpl<Filial, Integer> {

	public FilialDAOImpl() {
		super(Filial.class);
	}

	@SuppressWarnings("unchecked")
	public List<Filial> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new Filial().getClass());

		criteria.add(Restrictions.like("filiNome", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Filial> filiais = criteria.list();

		return filiais;

	}

}
