package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.Empresa;
import br.com.gestaoambiental.connection.HibernateUtil;

public class EmpresaDAOImpl extends DaoImpl<Empresa, Integer> {

	public EmpresaDAOImpl() {
		super(Empresa.class);
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new Empresa().getClass());

		criteria.add(Restrictions.like("emprNome", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Empresa> empresas = criteria.list();

		return empresas;

	}

}
