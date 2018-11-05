package main.java.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.domain.Comment;

public class CommentDAO {

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

	public Integer addComment(String author, String body) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer commentID = null;
		
		try {
			tx = session.beginTransaction();
			Comment comment = new Comment(author, body);
			commentID = (Integer) session.save(comment);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commentID;
	}


	/* Method to READ all the employees */
	public void listComments() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List comments = session.createQuery("FROM Comment").list();
			for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
				Comment comment = (Comment) iterator.next();
				System.out.print(" Author: " + comment.getAuthor());
				System.out.print(" Body: " + comment.getBody());
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
	public void updateComment(Integer commentID, String body) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Comment comment = (Comment) session.get(Comment.class, commentID);
			comment.setBody(body);
			session.update(comment);
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
	public void deleteComment(Integer commentID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Comment comment = (Comment) session.get(Comment.class, commentID);
			session.delete(comment);
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
