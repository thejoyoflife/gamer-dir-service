package com.example.gamer.directory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class CreditAlreadyAwardedException extends ErrorResponseException {
	private static final long serialVersionUID = 1L;

	public CreditAlreadyAwardedException(String message) {
		super(HttpStatus.CONFLICT, asProblemDetail(message), null);
	}
	
	private static ProblemDetail asProblemDetail(String message) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
	}
}
