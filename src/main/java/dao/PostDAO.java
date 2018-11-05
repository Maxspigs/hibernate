package main.java.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.domain.Post;
import main.java.domain.Tag;

public class PostDAO {

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

	public Long addPost(Post post) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long postID = null;
		
		try {
			tx = session.beginTransaction();
			postID = (Long) session.save(post);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return postID;
	}


	/* Method to READ all the employees */
	public void listPosts() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List posts = session.createQuery("FROM Post").list();
			for (Iterator iterator = posts.iterator(); iterator.hasNext();) {
				Post post = (Post) iterator.next();
				System.out.print(" Author: " + post.getAuthor());
				System.out.print(" Body: " + post.getBody());
				System.out.print(" Title: " + post.getTitle());
				System.out.println();
				String buffer = "Tag :";
				System.out.println("DEBUGGGGGGG DEBUGGGGGGGGGG : " + post.toString());
				for (Tag tag : post.getTags()) {
					System.out.println("DEBUGGGGGGGGGGGGGG DEBUGGGGGGGG TEST 2: " + tag.getText());
					buffer += tag.getText() + ", ";
				}
				System.out.println(buffer);
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
	public void updatePost(Integer postID, String body, String title) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Post post = (Post) session.get(Post.class, postID);
			post.setBody(body);
			post.setTitle(title);
			session.update(post);
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
	public void deletePost(Integer postID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Post post = (Post) session.get(Post.class, postID);
			session.delete(post);
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
