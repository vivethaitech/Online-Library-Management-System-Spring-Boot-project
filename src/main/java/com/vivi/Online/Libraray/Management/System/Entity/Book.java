package com.vivi.Online.Libraray.Management.System.Entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
    private String author;
    private boolean available; 
    private String publisher;
	private int publishedYear;
    
	public Book(String title, String author,boolean available,String publisher,int publishedYear) {

		this.title = title;
		this.author = author;
		this.publisher=publisher;
		this.publishedYear=publishedYear;
		this.available=available;
		
	}

	public Book() {
	}
	
    @ManyToMany(mappedBy = "books")
    private Set<UserEntity> users;

	public Book(String title2, String author2, Object publisher2, Integer publishedYear2) {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
	
}
