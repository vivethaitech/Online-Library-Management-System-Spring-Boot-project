package com.vivi.Online.Libraray.Management.System.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vivi.Online.Libraray.Management.System.Entity.Book;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.AlreadyBookExistException;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.BookNotFoundException;
import com.vivi.Online.Libraray.Management.System.model.BookModel;
import com.vivi.Online.Libraray.Management.System.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
@Tag(name = "Book Controller", description = "Controller for book operations")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	private BookService service;

	@Autowired
	public BookController(BookService service) {
		this.service = service;
	}

	
    @Operation(summary = "Get all books")
    @GetMapping("/get")
    public String getBooks(Model model) {
        logger.info("Fetching all books");
        List<Book> books = service.getBooks();
        model.addAttribute("books", books);
        return "library/bookList";
    }
    
	// to add data
	
	@GetMapping("/add")
	@PreAuthorize("hasRole('ROLE_INCHARGE')")
	public String bookForm(Model model) {
		BookModel book = new BookModel();
		model.addAttribute("book", book);
		return "library/BookForm";
	}

	
	    @PostMapping("/post")
	    public String addBook(@ModelAttribute @Valid BookModel bookModel,BindingResult result,Model model) {
	    	if(result.hasErrors()) 
	      {
	    		model.addAttribute("errors", result.getAllErrors());
	    		return "library/BookFormError";
	      }
	    	Book book = new Book(bookModel.getTitle(),bookModel.getAuthor(), bookModel.getAvailable(),bookModel.getPublisher(),bookModel.getPublishedYear());
	        logger.info("Adding book: {}", book.getTitle());
	        try 
	        {
	            service.addBooks(book);
	            return "library/success";
	        } 
	        catch (AlreadyBookExistException e) {
	            logger.error("Book already exists: {}", book.getTitle());
	            return "library/alreadyError";
	    }
}

	// to update data
    @GetMapping("/update/{title}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateBookForm(@PathVariable String title, Model model) {
        logger.info("Fetching book for update: {}", title);
        Book book = service.findBookByTitle(title);
        if (book == null) {
            logger.error("Book not found: {}", title);
            return "library/error";
        }
        model.addAttribute("book", book);
        return "library/updateBookForm";
    }

    @PostMapping("/update/{title}")
    @Operation(summary = "Update book")
    public String updateBook(@PathVariable String title, @ModelAttribute Book book) {
        logger.info("Updating book: {}", title);
        try {
            service.updateBookByTitle(title, book);
            return "redirect:/book/get";
        } catch (BookNotFoundException ex) {
            logger.error("Book not found: {}", title);
            return "library/error";
        }
    }
	  //to delete data
	  
	  @GetMapping("delete/{title}")  

	  @PreAuthorize("hasRole('ROLE_MANAGER')")
	  public String deleteBook(@PathVariable String title,Model model) 
	  { 
	  model.addAttribute("title",title); 
	  return "library/confirmDelete"; 
	  }
	   
	@PostMapping("/delete/{title}")

    public String deleteBook(@PathVariable String title) {
        logger.info("Deleting book: {}", title);
        try {
            service.deleteBookByTitle(title);
            return "redirect:/book/get";
        } catch (Exception ex) {
            logger.error("Error deleting book: {}", title, ex);
            return "library/error";
        }
}
}
