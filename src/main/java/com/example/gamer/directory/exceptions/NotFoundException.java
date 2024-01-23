package com.example.gamer.directory.exceptions;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import static com.example.gamer.directory.exceptions.ErrorConstants.*; 

public class NotFoundException extends ErrorResponseException {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

	public NotFoundException(String message) {
		super(STATUS, asProblemDetail(message), null);
	}
	
	private static ProblemDetail asProblemDetail(String message) {
		var pb = ProblemDetail.forStatusAndDetail(STATUS, message);
		pb.setType(URI.create(NOT_FOUND_TYPE));
		return pb;
	}
}
