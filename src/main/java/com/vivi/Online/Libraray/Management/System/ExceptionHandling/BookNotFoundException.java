package com.vivi.Online.Libraray.Management.System.ExceptionHandling;

public class BookNotFoundException extends RuntimeException{
	
	public BookNotFoundException(String message) {
		super(message);
	}

}
