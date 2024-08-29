package com.vivi.Online.Libraray.Management.System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivi.Online.Libraray.Management.System.Entity.Book;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.AlreadyBookExistException;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.BookNotFoundException;
import com.vivi.Online.Libraray.Management.System.repository.BookRepository;
@Service
public class BookService {
	
	private BookRepository repo;
	@Autowired
	public BookService(BookRepository repo) {
		this.repo=repo;
	}
	public List<Book> getBooks() {
	
		return repo.findAll();
	}
	public Book findBookByTitle(String title) {
	    return repo.findByTitle(title);
	          
	}
	public String addBooks(Book book) {
		List<Book> list=repo.findAll();
		for(Book books: list)
			if(books.getTitle().equals(book.getTitle())) {
				throw new AlreadyBookExistException("The book you tried to enter is already available");
			}
		repo.save(book);
		return "Book Successfully added";
	}
	public String updateBookByTitle(String title, Book book) {
	    Book existingBook = repo.findByTitle(title);
	    if (existingBook != null) {
	        existingBook.setAuthor(book.getAuthor());
	        existingBook.setAvailable(book.isAvailable());
	        existingBook.setPublisher(book.getPublisher());
	        existingBook.setPublishedYear(book.getPublishedYear());
	        repo.save(existingBook);
	        return "Book details successfully updated";
	    } else {
	        throw new BookNotFoundException("Book with title '" + title + "' not found");
	    }
	}


	  public void deleteBookByTitle(String title) {
	        List<Book> books = repo.findAll();
	        for (Book book : books) {
	            if (book.getTitle().equals(title)) {
	                repo.delete(book);
	                return;
	            }
	        }
	        throw new BookNotFoundException("Book with title '" + title + "' not found");
	    }

}
