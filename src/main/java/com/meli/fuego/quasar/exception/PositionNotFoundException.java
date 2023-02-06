package com.meli.fuego.quasar.exception;

import org.springframework.http.HttpStatus;

public class PositionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	protected HttpStatus httpStatus;

	public PositionNotFoundException(final String message) { 
		super(message); 
	}

}
