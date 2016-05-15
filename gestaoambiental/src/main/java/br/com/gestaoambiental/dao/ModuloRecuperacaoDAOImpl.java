package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.FormaProtecao;
import br.com.gestaoambiental.bean.ModuloRecuperacao;
import br.com.gestaoambiental.connection.HibernateUtil;

public class ModuloRecuperacaoDAOImpl extends DaoImpl<ModuloRecuperacao, Integer> {

	public ModuloRecuperacaoDAOImpl() {
		super(ModuloRecuperacao.class);
	}

	@SuppressWarnings("unchecked")
	public List<ModuloRecuperacao> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new ModuloRecuperacao().getClass());

		criteria.add(Restrictions.like("moreDescricao", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<ModuloRecuperacao> modulos = criteria.list();

		return modulos;

	}

}
