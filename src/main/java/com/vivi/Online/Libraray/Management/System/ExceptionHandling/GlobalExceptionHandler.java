package com.vivi.Online.Libraray.Management.System.ExceptionHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> body = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            body.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

	


