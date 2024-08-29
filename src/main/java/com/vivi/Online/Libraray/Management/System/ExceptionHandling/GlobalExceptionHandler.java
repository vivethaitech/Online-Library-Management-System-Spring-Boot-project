package com.vivi.Online.Libraray.Management.System.ExceptionHandling;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBookNotFoundException(BookNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Ensure this matches the name of your error Thymeleaf template
    }

    @ExceptionHandler(AlreadyBookExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyBookExistException(AlreadyBookExistException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Ensure this matches the name of your error Thymeleaf template
    }
}

	
	/*
	 * @ExceptionHandler(AlreadyBookExistException.class)
	 * 
	 * @ResponseStatus(HttpStatus.CONFLICT) public ResponseEntity<Object>
	 * handle(AlreadyBookExistException ae){ Map<String,Object> body = new
	 * LinkedHashMap<>(); body.put("message", ae.getMessage());
	 * body.put("http status", HttpStatus.CONFLICT.value()); return new
	 * ResponseEntity<>(body,HttpStatus.CONFLICT); }
	 * 
	 * @ExceptionHandler(BookNotFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.CONFLICT) public ResponseEntity<Object>
	 * handle(BookNotFoundException ae){ Map<String,Object> body = new
	 * LinkedHashMap<>(); body.put("message", ae.getMessage());
	 * body.put("http status", HttpStatus.CONFLICT.value()); return new
	 * ResponseEntity<>(body,HttpStatus.CONFLICT); }
	 * 
	 * @ExceptionHandler(AlreadyBookExistException.class) public String
	 * handleAlreadyBookExistException(AlreadyBookExistException e, Model model) {
	 * model.addAttribute("error", e.getMessage()); return "error"; }
	 */


