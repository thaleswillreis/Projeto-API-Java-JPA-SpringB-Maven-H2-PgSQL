package com.homeitz.course.services.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) { //recebe a msg e repassa para a super classe RuntimeException
		super(msg);
	}
}
