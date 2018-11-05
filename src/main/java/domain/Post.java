package main.java.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "title")
	private String title;
	/*
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
	@Column(name = "comments")
	private List<Comment> comments = new ArrayList<>();
	*/
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "post")
	private List<Tag> tags = new ArrayList<>();
	
	public Post(String author, String body, String title) {
		super();
		this.author = author;
		this.body = body;
		this.title = title;
	}
	
	public Post() {}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	*/
	public Long getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", author=" + author + ", body=" + body + ", title=" + title + ", tags=" + tags + "]";
	}
	
	
	
	
}
