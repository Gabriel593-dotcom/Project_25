package com.example.project_25.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.project_25.services.exceptions.ResourceAlreadyExistsException;
import com.example.project_25.services.exceptions.ResourceNotFoundException;

//A @ControllerAdviceanotação foi introduzida pela primeira vez no Spring 3.2. Ele permite que você trate de exceções em
//todo o aplicativo, não apenas em um controlador individual. Você pode pensar nele como um interceptorde exceções lançadas
//por métodos anotados com @RequestMapping ou os atalhos (getMapping, PutMapping...)
//fonte: https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f#:~:text=The%20%40ControllerAdvice%20annotation%20was%20first,or%20one%20of%20the%20shortcuts.
@ControllerAdvice
public class ExceptionHandler {

	private ResponseEntity<StandardError> handlerBuilder(String error, HttpStatus status, Exception e, HttpServletRequest request){
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(status).body(standardError);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(HttpServletRequest request, ResourceNotFoundException notFoundException){
		String error = "Not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handlerBuilder(error, status, notFoundException, request); 
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<StandardError> resourceAlreadyExists(HttpServletRequest request, ResourceAlreadyExistsException e){
		String error = "Resource already exists";
		HttpStatus status = HttpStatus.CONFLICT;
		return handlerBuilder(error, status, e, request);
	}
}

