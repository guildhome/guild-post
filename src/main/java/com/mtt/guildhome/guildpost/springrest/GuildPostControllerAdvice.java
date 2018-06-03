package com.mtt.guildhome.guildpost.springrest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mtt.guildhome.guildpost.domain.ErrorInfo;

@ControllerAdvice
public class GuildPostControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorDetails = "";
		for (FieldError e : ex.getBindingResult().getFieldErrors()) {
			if (e.getDefaultMessage() != null) {
				errorDetails += e.getDefaultMessage() + "|";
			}
		}
		return new ResponseEntity<>(new ErrorInfo("Validation Error", errorDetails), HttpStatus.BAD_REQUEST);
	}

}
