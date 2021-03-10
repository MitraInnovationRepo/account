package com.mitralabs.customer.error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ErrorResponse onConstraintValidationException(ConstraintViolationException e) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

		for (ConstraintViolation violation : e.getConstraintViolations()) {
			errorResponse.setViolations(violation.getPropertyPath().toString() + " " + violation.getMessage());
		}
		return errorResponse;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errorResponse.setViolations(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		return errorResponse;
	}

}
