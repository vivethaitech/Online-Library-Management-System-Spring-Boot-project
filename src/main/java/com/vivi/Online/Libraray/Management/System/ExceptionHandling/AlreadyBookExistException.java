package com.vivi.Online.Libraray.Management.System.ExceptionHandling;

public class AlreadyBookExistException extends RuntimeException {
	
	public AlreadyBookExistException(String message) {
		super(message);
	}

}
