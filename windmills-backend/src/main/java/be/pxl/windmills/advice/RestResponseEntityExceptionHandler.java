package be.pxl.windmills.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { DateTimeParseException.class })
	protected ResponseEntity<Object> handleNotAcceptable(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(),
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
