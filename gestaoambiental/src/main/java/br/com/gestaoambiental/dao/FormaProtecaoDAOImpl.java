package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.FormaProtecao;
import br.com.gestaoambiental.connection.HibernateUtil;

public class FormaProtecaoDAOImpl extends DaoImpl<FormaProtecao, Integer> {

	public FormaProtecaoDAOImpl() {
		super(FormaProtecao.class);
	}

	@SuppressWarnings("unchecked")
	public List<FormaProtecao> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new FormaProtecao().getClass());

		criteria.add(Restrictions.like("foprDescricao", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<FormaProtecao> equipamentos = criteria.list();

		return equipamentos;

	}

}
