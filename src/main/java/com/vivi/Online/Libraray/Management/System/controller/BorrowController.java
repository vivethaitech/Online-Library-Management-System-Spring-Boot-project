package com.vivi.Online.Libraray.Management.System.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivi.Online.Libraray.Management.System.Entity.Book;
import com.vivi.Online.Libraray.Management.System.Entity.Borrower;
import com.vivi.Online.Libraray.Management.System.Entity.UserEntity;
import com.vivi.Online.Libraray.Management.System.repository.BookRepository;
import com.vivi.Online.Libraray.Management.System.repository.BorrowerRepository;
import com.vivi.Online.Libraray.Management.System.repository.UserRepository;

@Controller
public class BorrowController {

    // Correct logger initialization
    private static final Logger logger = LoggerFactory.getLogger(BorrowController.class);

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/borrow/submit")
    public String borrowBook(@RequestParam("bookTitle") String bookTitle, 
                             @RequestParam("userName") String userName, 
                             Model model) {
        // Log the borrow request details
        logger.info("Borrow request received: BookTitle = {}, UserName = {}", bookTitle, userName);

        // Find the book by title
        Book book = bookRepository.findByTitle(bookTitle);
        // Find the user by name
        UserEntity user = userRepository.findByName(userName);

        // Check if the book exists, the user exists, and the book is available
        if (book != null && user != null && book.isAvailable()) {
            // Create a new borrower record
            Borrower borrower = new Borrower(bookTitle, userName, book, user);
            borrowerRepository.save(borrower);

            // Update the book's availability to false (indicating it has been borrowed)
            book.setAvailable(false);
            bookRepository.save(book); // Save the updated book entity

            model.addAttribute("message", "Book borrowed successfully!");
        } else if (book != null && !book.isAvailable()) {
            model.addAttribute("error", "Book is currently unavailable!");
        } else {
            model.addAttribute("error", "Book or User not found!");
        }
        return "redirect:/library/welcome";
    }
}
