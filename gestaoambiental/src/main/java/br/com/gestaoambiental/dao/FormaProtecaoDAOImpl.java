package br.com.gestaoambiental.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gestaoambiental.bean.FormaProtecao;
import br.com.gestaoambiental.connection.HibernateUtil;

public class FormaProtecaoDAOImpl implements FormaProtecaoDAO {
	
	private Transaction trns = null;
		
	public void save(FormaProtecao formaProtecao) {	
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {			
			trns = session.beginTransaction();
			session.save(formaProtecao);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}		
	}	
	
	public void update(FormaProtecao formaProtecao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(formaProtecao);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}		
	}

	public void delete(int id) {	
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			FormaProtecao user = (FormaProtecao) session.load(FormaProtecao.class, new Integer(id));
			session.delete(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
	}

	public List<FormaProtecao> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<FormaProtecao> users = new ArrayList<FormaProtecao>();		
		try {
			trns = session.beginTransaction();
			users = session.createQuery("from FormaProtecao").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return users;
	}

	public FormaProtecao load(int id) {
		FormaProtecao formaProtecao = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String queryString = "from FormaProtecao where foprId = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			formaProtecao = (FormaProtecao) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return formaProtecao;
	}
	
//	public static void main(String args[]){
//		FormaProtecaoDAOImpl dao = new FormaProtecaoDAOImpl();
//		FormaProtecao formaProtecao = new FormaProtecao();
//		formaProtecao.setFoprDescricao("Teste");
//		dao.save(formaProtecao);
//		
//		formaProtecao = dao.load(formaProtecao.getFoprId());
//		
//		
//		dao.delete(3);
//		
//		
//		formaProtecao.setFoprDescricao("Teste update");
//		dao.update(formaProtecao);
//		
//		for(FormaProtecao pro: dao.findAll()){
//			System.out.println(pro.getFoprDescricao());
//		}
//		
//	}
}
