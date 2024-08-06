package br.com.neki.sistema_skill.exceptions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.common.lang.Nullable;

@RestControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handleEntidadeNotFoundException(EntityNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "Error: " + e.getLocalizedMessage());

        problemDetail.setTitle("Resource not found");
        problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
        return problemDetail;
    }
	
	@ExceptionHandler(SkillAlreadyExistsException.class)
	ProblemDetail handleSkillAlreadyExistsException(SkillAlreadyExistsException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
				"Error: " + e.getLocalizedMessage());
		
		problemDetail.setTitle("Skill already exists");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/conflict"));
		return problemDetail;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	ProblemDetail handleNoSuchElementException(NoSuchElementException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Resource not Found");
		problemDetail.setType(URI.create("https://api.ecommerce.com/errors/not-found"));
		return problemDetail;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

		if (response.getBody() instanceof ProblemDetail problemDetailBody) {
			problemDetailBody.setProperty("message", ex.getMessage());
			if (ex instanceof MethodArgumentNotValidException subEx) {
				BindingResult result = subEx.getBindingResult();
				problemDetailBody.setType(URI.create("http://api.ecommerce.com/erros/argument-not-valid"));
				problemDetailBody.setTitle("Request error");
				problemDetailBody.setDetail("An error occurred while processing the Request");
				problemDetailBody.setProperty("message", "Object Validation Failed" + result.getObjectName() + "'. "
						+ "Number of Erros: " + result.getErrorCount());
				List<FieldError> fldErros = result.getFieldErrors();
				List<String> erros = new ArrayList<>();

				for (FieldError obj : fldErros) {
					erros.add("Field: " + obj.getField() + " - Error: " + obj.getDefaultMessage());
				}
				problemDetailBody.setProperty("Erros Found", erros.toString());
			}
		}
		return response;
	}
}
