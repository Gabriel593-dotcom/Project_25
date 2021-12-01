package com.example.project_25.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("The resource if you want ins't here.");
	}
}
