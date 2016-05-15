package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.TempoGuarda;
import br.com.gestaoambiental.connection.HibernateUtil;

public class TempoGuardaDAOImpl extends DaoImpl<TempoGuarda, Integer> {

	public TempoGuardaDAOImpl() {
		super(TempoGuarda.class);
	}

	@SuppressWarnings("unchecked")
	public List<TempoGuarda> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new TempoGuarda().getClass());

		criteria.add(Restrictions.like("teguDescricao", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<TempoGuarda> tempos = criteria.list();

		return tempos;

	}

}
