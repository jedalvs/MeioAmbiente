package br.com.gestaoambiental.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import br.com.gestaoambiental.connection.HibernateUtil;

public class DaoImpl<E, ID extends Serializable> implements
		Dao<E, Serializable> {

	protected Session session;

	protected Criteria criteria;
	protected Query query;

	private Class<E> entity;

	public DaoImpl(Class<E> entity) {
		this.entity = entity;
	}

	public void delete(E entity) {
		try {
			
			HibernateUtil.beginTransaction();
			session = HibernateUtil.getCurrentSession();
			session.delete(entity);

			HibernateUtil.commitTransaction();

		} catch (ConstraintViolationException e) {
			HibernateUtil.rollbackTransaction();
			throw new ConstraintViolationException("Constraint violation",
					e.getSQLException(), "");
		} catch (JDBCConnectionException e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();

			e.printStackTrace();
		}
		// } finally {
		// session = HibernateUtil.closeInstance();
		// }
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() throws Exception {

		session = HibernateUtil.getCurrentSession();
		criteria = session.createCriteria(entity);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public void save(E entity) {
		try {
			
			HibernateUtil.beginTransaction();
			session = HibernateUtil.getCurrentSession();
			session.save(entity);
			HibernateUtil.commitTransaction();

		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
		}
		// } finally {
		// session = HibernateUtil.closeInstance();
		// }
	}

	public void update(E entity) throws Exception {
		try {
			
			HibernateUtil.beginTransaction();
			session = HibernateUtil.getCurrentSession();
			session.merge(entity);
			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
		}
		// finally {
		// session = HibernateUtil.closeInstance();
		// }
	}

	@SuppressWarnings("unchecked")
	public E load(Serializable key) throws Exception {

		session = HibernateUtil.getCurrentSession();

		return (E) session.get(this.entity, (Serializable) key);
	}

}
