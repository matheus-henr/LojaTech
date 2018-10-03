package br.com.lojatech.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.lojatech.services.exception.DataIntegrityException;
import br.com.lojatech.services.exception.ObjectNotFoundException;
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(e.getMessage(),HttpStatus.NOT_FOUND.value(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(ObjectNotFoundException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(e.getMessage(),HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		
		ValidationError err = new ValidationError("Erro de Validação",HttpStatus.BAD_REQUEST
				.value(), System.currentTimeMillis());
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
}
