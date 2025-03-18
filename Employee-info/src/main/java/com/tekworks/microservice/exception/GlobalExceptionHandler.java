package com.tekworks.microservice.exception;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		Map<String, String> errors = new HashMap<>();

		bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(value = NoEmployeeFoundException.class)
	public ResponseEntity<Error> handleNoEMployeeFoundException(NoEmployeeFoundException ex) {

		Error error = new Error();
		error.setMessage(ex.getMessage());
		error.setExceptionDate(LocalDate.now().toString());
		error.setExceptionTime(LocalTime.now().toString());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<Error> handleServiceUnavailableException(ServiceUnavailableException ex) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(new Error(ex.getMessage(), LocalDate.now().toString(), LocalTime.now().toString()));
	}
}
