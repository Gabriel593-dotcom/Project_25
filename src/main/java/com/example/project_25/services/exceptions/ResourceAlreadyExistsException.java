package com.example.project_25.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistsException() {
		super("This Resource already exists in database.");
	}

}
