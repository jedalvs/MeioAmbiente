package br.com.gestaoambiental.connection;

import java.util.Hashtable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	private static Hashtable<String, Session> hashTableSession = new Hashtable<String, Session>();

	private static ThreadLocal<Transaction> transactionThread = new ThreadLocal<Transaction>();

	private static Log log = LogFactory.getLog("HibernateUtil.class");

	static {

		try {

			sessionFactory = new Configuration().configure(
					"hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable ex) {

			log.error("HibernateUtil: Initial SessionFactory creation failed.");
			throw new ExceptionInInitializerError(ex);
		}

	}

	public static String obterIDSessao() {
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) fCtx.getExternalContext()
				.getSession(false);

		return httpSession.getId();

	}

	/**
	 * Método estático responsável por abrir a sessão e buscar a instância do
	 * tipo Session
	 * 
	 * @return Session
	 */
	public static Session getCurrentSession() {

		String httpSessionID = obterIDSessao();

		Session s = (Session) hashTableSession.get(httpSessionID);
		if (s == null || !s.isOpen()) {
			s = sessionFactory.openSession();
			hashTableSession.put(httpSessionID, s);
		}

		/*
		 * s.flush(); s.clear();
		 */

		return s;

	}

	/**
	 * Método estático responsável por fechar a sessão
	 * 
	 */
	public static void closeCurrentSession() {

		Transaction tx = transactionThread.get();

		if (tx != null) {
			commitTransaction();
		} else {
			closeSession();
		}
	}

	public static void closeSession() {

		String httpSessionID = obterIDSessao();

		try {

			Session s = (Session) hashTableSession.get(httpSessionID);
			if (s != null && s.isOpen()) {
				s.close();
				s = null;
			}

		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		} finally {
			hashTableSession.remove(httpSessionID);
		}

	}

	public static void beginTransaction() {

		String httpSessionID = obterIDSessao();

		Transaction tx = transactionThread.get();

		try {

			if (tx != null && !tx.isActive()) {
				tx = null;
				transactionThread.set(null);
			}

			if (tx == null) {
				Session s = (Session) hashTableSession.get(httpSessionID);
				if (s != null && s.isOpen()) {
					closeSession();
				}

				tx = getCurrentSession().beginTransaction();
				transactionThread.set(tx);
			}

		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		} finally {
			Session s = (Session) hashTableSession.get(httpSessionID);
			if (s == null || !s.isOpen()) {
				getCurrentSession();
			} else {
				s.clear();
			}
		}

	}

	public static void commitTransaction() {

		Transaction tx = transactionThread.get();

		try {

			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				Session s = getCurrentSession();
				s.flush();
				tx.commit();
				tx = null;
			}

		} catch (HibernateException ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		} finally {
			transactionThread.set(null);
			closeSession();
		}

	}

	public static void rollbackTransaction() {

		Transaction tx = transactionThread.get();

		try {

			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
				tx = null;
			}

		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		} finally {
			transactionThread.set(null);
			closeSession();
		}

	}

}
