package br.com.gestaoambiental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gestaoambiental.bean.Usuario;
import br.com.gestaoambiental.connection.HibernateUtil;

public class UsuarioDAOImpl extends DaoImpl<Usuario, Integer> {

	public UsuarioDAOImpl() {
		super(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByDescricao(String descricao)
			throws Exception {
		session = HibernateUtil.getCurrentSession();

		criteria = session.createCriteria(new Usuario().getClass());

		criteria.add(Restrictions.like("usuaNome", "%" + descricao + "%"));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Usuario> usuarios = criteria.list();

		return usuarios;

	}

}
