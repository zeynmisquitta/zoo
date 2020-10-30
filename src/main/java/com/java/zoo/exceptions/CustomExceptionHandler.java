
package com.java.zoo.exceptions;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {

		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ CustomAnimalException.class, CustomRoomException.class })
	protected ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {

		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder errorMsgStrBuilder = new StringBuilder();
		ex.getBindingResult().getAllErrors()
				.forEach(error -> errorMsgStrBuilder.append(error.getDefaultMessage()).append(","));
		String errorMsgs = errorMsgStrBuilder.deleteCharAt(errorMsgStrBuilder.length() - 1).toString();

		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), errorMsgs,
				request.getDescription(false));
		return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
	}

	/*
	 * To add a body to the existing exception responses present in the
	 * ResponseEntityExceptionHandler
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Object apiError = Objects.isNull(body)
				? new CustomExceptionResponse(new Date(), exception.getLocalizedMessage(),
						request.getDescription(false))
				: body;
		return super.handleExceptionInternal(exception, apiError, headers, status, request);
	}
}
