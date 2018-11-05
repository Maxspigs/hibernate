package main.java.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.domain.Tag;

public class TagDAO {

	private static SessionFactory factory;

	public SessionFactory startFactorySession() {	
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return factory;		
	}
	
	public void closeFactorySession() {
		factory.close();
	}

	public Integer addTag(Tag tag) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer tagID = null;
		
		try {
			tx = session.beginTransaction();
			tagID = (Integer) session.save(tag);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tagID;
	}


	/* Method to READ all the employees */
	public void listTags() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List tags = session.createQuery("FROM Tag").list();
			for (Iterator iterator = tags.iterator(); iterator.hasNext();) {
				Tag tag = (Tag) iterator.next();
				System.out.print(" Name: " + tag.getText());
				System.out.println();
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateTag(Integer tagID, String text) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Tag tag = (Tag) session.get(Tag.class, tagID);
			tag.setText(text);
			session.update(tag);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteTag(Integer tagID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Tag tag = (Tag) session.get(Tag.class, tagID);
			session.delete(tag);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
