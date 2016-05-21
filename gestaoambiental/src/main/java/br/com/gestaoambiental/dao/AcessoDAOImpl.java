package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.Acesso;
import br.com.gestaoambiental.connection.HibernateUtil;

public class AcessoDAOImpl extends DaoImpl<Acesso, Integer> {

	public AcessoDAOImpl() {
		super(Acesso.class);
	}

	@SuppressWarnings("unchecked")
	public List<Acesso> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new Acesso().getClass());

		criteria.add(Restrictions.like("acesDescricao", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Acesso> acessos = criteria.list();

		return acessos;

	}

}
