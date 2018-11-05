import main.java.dao.PostDAO;
import main.java.dao.TagDAO;
import main.java.dao.UserDAO;
import main.java.domain.Post;
import main.java.domain.Tag;

public class Blog {
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		userDAO.startFactorySession();	
		userDAO.addUser("Maxime Pigeon", "Blademax838@hotmail.com");
		userDAO.addUser("Steve Pigeon", "Spigs8@hotmail.com");
		userDAO.addUser("Serge Pigeon", "serge2001@hotmail.com");
		userDAO.listUsers();
		
		TagDAO tagDAO = new TagDAO();
		tagDAO.startFactorySession();
		Tag t1 = new Tag("Cannabis");
		Tag t2 = new Tag("Sativa");
		Tag t3 = new Tag("Indica");
		Tag t4 = new Tag("THC");
		Tag t5 = new Tag("Hybride");
		//tagDAO.addTag(t1);
		//tagDAO.addTag(t2);
		//tagDAO.addTag(t3);
		//tagDAO.addTag(t4);
		//tagDAO.addTag(t5);
		//tagDAO.listTags();
		
		
		Post post = new Post("Maxime Pigeon", "testBody", "testTitle");
		post.getTags().add(t1);
		post.getTags().add(t2);
		PostDAO postDAO = new PostDAO();
		postDAO.startFactorySession();
		postDAO.addPost(post);
		postDAO.listPosts();
		
		
		
		/* Add few employee records in database */
		//Integer empID1 = userDAO.addEmployee("Zara", "Ali", 1000, "F");
		//Integer empID2 = userDAO.addEmployee("Daisy", "Das", 5000, "F");
		//Integer empID3 = userDAO.addEmployee("John", "Paul", 10000, "M");

	
		/* List down all the employees */
		//userDAO.listEmployees();

		/* Update employee's records */
		//userDAO.updateEmployee(empID1, 5000);

		/* Delete an employee from the database */
		//userDAO.deleteEmployee(empID2);
		//userDAO.listEmployees();
	}
}
