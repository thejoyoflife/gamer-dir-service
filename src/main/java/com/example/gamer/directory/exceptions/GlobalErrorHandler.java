package com.example.gamer.directory.exceptions;

import static java.util.stream.Collectors.joining;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.gamer.directory.exceptions.ErrorConstants.*;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
										HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ProblemDetail problem = createInvalidInputProblemDetail(ex.getMessage());
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
										HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var detail = "Something not valid in the input"; 
		if (ex.getBindingResult().getFieldErrorCount() > 0) {
			detail = ex.getBindingResult().getFieldErrors()
						.stream()
						.map(fe -> String.format("%s: %s", fe.getField(), fe.getDefaultMessage()))
						.collect(joining("; "));
		} else if (ex.getErrorCount() > 0) {
			detail = ex.getAllErrors().stream()
						.map(oe -> oe.getDefaultMessage())
						.collect(joining("; "));
		}
		
		ProblemDetail problem = createInvalidInputProblemDetail(detail);
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ProblemDetail createInvalidInputProblemDetail(String detail) {
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
		pd.setType(URI.create(INVALID_INPUT_TYPE));
		pd.setTitle("Invalid Input");
		return pd;
	}
}
