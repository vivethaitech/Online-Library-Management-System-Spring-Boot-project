package com.vivi.Online.Libraray.Management.System.ExceptionHandling;

public class EmailAlreadyExist extends RuntimeException {
	
	public EmailAlreadyExist(String message) {
		super(message);
	}

}
