package com.i2g.rms.rest.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.i2g.rms.rest.model.error.ErrorRO;
import com.i2g.rms.service.exception.ResourceAlreadyExistsException;
import com.i2g.rms.service.exception.ResourceConflictException;
import com.i2g.rms.service.exception.ResourceLockedException;
import com.i2g.rms.service.exception.ResourceNotCreatedException;
import com.i2g.rms.service.exception.ResourceNotFoundException;
import com.i2g.rms.service.exception.ResourceNotRemovedException;
import com.i2g.rms.service.exception.ResourceNotUpdatedException;
import com.i2g.rms.service.exception.ResourceNotValidException;
import com.i2g.rms.service.message.MessageBuilder;

/**
 * Spring MVC {@link ControllerAdvice} implementation which handles generating 
 * the appropriate error responses to a REST client for any exceptions which 
 * occur while handling a RESTful request.  Errors typically fall into two broad 
 * categories: client request errors and server processing errors.  For client
 * request errors, HTTP status codes in the family of 4XX should be returned; 
 * for any server processing errors, one of the 5XX status codes should be 
 * returned.
 * 
 * <p>The exception processor uses the Spring MVC {@link ExceptionHandler}
 * annotation to define the types of exception which should be routed to a
 * particular method for processing.  Each annotation can take multiple 
 * exception classes allowing for common processing of related error conditions.
 * An {@link ErrorRO} may be returned with the response containing detailed 
 * information for the REST client to display any pertinent information to the
 * end user.  The following list provides a high level guide for the different 
 * error types and their associated status codes.
 * 
 * <p><strong>Client Request Errors</strong>
 * <ul>
 *	<li>{@code 400 - BAD REQUEST} - incorrect JSON format, JSON parse errors,
 *		type mismatch (e.g. character data in integer field), etc
 *	<li>{@code 403 - FORBIDDEN} - client is not authenticated or authorized
 *	<li>{@code 404 - NOT FOUND} - client requested data which doesn't exist
 *	<li>{@code 405 - METHOD NOT ALLOWED} - client requested a resource method
 *		which is not supported (GET, PUT, DELETE, POST, PATCH, UPDATE, etc)
 *	<li>{@code 406 - NOT ACCEPTABLE} - validation errors for a client request
 *	<li>{@code 409 - CONFLICT} - client request to create an already existing
 *		resource or if a resource is locked and unavailable
 * </ul>
 * 
 * <p><strong>Server Response Errors</strong>
 * <ul>
 *	<li>{@code 500 - INTERNAL SERVER ERROR} - 
 *	<li>{@code 503 - SERVICE UNAVAILABLE} - 
 * </ul>
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@ControllerAdvice
public class ExceptionProcessor {
	
	/** Service for retrieving dynamic error messages. */
	@Autowired
	private MessageBuilder _messageService;
	
	// NOTE TO DEVELOPERS: Please maintain the methods in the order of the
	// HTTP status code returned (e.g. 400, 403, 404, etc) for ease of reference
	//
	// As these methods are used for processing and returning appropriate errors
	// to the client, it's imperative they do not throw new exceptions during
	// processing which prevent the original error from being returned to the
	// client; please code carefully!  Avoid null pointers, etc...
	//
	// Any changes to the error processing requires associated spec and client
	// UI changes; DO NOT MODIFY this class without discussing with a lead
	// developer and business analysts!!!
	
	/**
	 * Returns an HTTP {@code 400 - BAD REQUEST} status and an {@link ErrorRO} 
	 * containing a list of all the binding result errors found while attempting 
	 * to parse the bad request.
	 *
	 * @param request
	 * @param ex
	 * @return list of errors generated from invalid parameters
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorRO invalidRequest(final HttpServletRequest request, final MethodArgumentNotValidException ex) {
		// Compile a single list of error messages found in the binding result
		final List<String> errors = new ArrayList<>();
		for( final FieldError error : ex.getBindingResult().getFieldErrors() ) {
			// TODO Create a REST-specific MessageId for this error
			errors.add(error.getObjectName() + "." + error.getField() + ": " + error.getDefaultMessage());
		}
		for( final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			// TODO Create a REST-specific MessageId for this error
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return new ErrorRO(errors);
	}
	
	/**
	 * Returns an HTTP {@code 400 - BAD REQUEST} status and an {@link ErrorRO} 
	 * describing the bad request (e.g. a type mismatch, mapping error, or 
	 * request parse error).
	 * 
	 * @param request
	 * @param ex
	 * @return description of error
	 */
	@ExceptionHandler({
		JsonMappingException.class, 
		TypeMismatchException.class, 
		HttpMessageNotReadableException.class,
		ResourceNotValidException.class,
		NumberFormatException.class,
		ResourceNotCreatedException.class,
		ResourceNotUpdatedException.class,
		ResourceNotRemovedException.class
	})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorRO badRequest(final HttpServletRequest request, final Exception ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Failed to process request"));
	}
	
	/**
	 * Returns an HTTP {@code 403 - FORBIDDEN} status and an {@link ErrorRO} 
	 * indicating the request is not authorized or authenticated.
	 * 
	 * @param request
	 * @param ex
	 * @return unauthenticated or unauthorized message
	 */
	@ExceptionHandler({
		AuthenticationException.class,
		AccessDeniedException.class
	})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorRO unauthorized(final HttpServletRequest request, final Exception ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Forbidden"));
	}
	
	/**
	 * Returns an HTTP {@code 404 - NOT FOUND} status and an {@link ErrorRO} 
	 * describing resource which was not found.
	 * 
	 * @param request
	 * @param ex
	 * @return a list of errors generated from unsupported functionality
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorRO resourceNotFound(final HttpServletRequest request, final Exception ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Resource not found"));
	}
	
	/**
	 * Returns an HTTP {@code 405 - METHOD NOT ALLOWED} status and an 
	 * {@link ErrorRO} describing unsupported/not implemented functionality.
	 * 
	 * @param request
	 * @param ex
	 * @return error description
	 */
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorRO unsupportedOperation(final HttpServletRequest request, final Exception ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Unsupported operation"));
	}
	
	/**
	 * Returns an HTTP {@code 409 - CONFLICTED} status and an {@link ErrorRO} 
	 * describing what the conflict is (resource is locked, etc).
	 * 
	 * @param request
	 * @param ex
	 * @return error description
	 */
	@ExceptionHandler({
		ResourceLockedException.class,
		ResourceConflictException.class,
		IllegalArgumentException.class,
		ConstraintViolationException.class,
		org.hibernate.exception.ConstraintViolationException.class,
		DataRetrievalFailureException.class, // TODO: Evaluate if this is the correct HTTP Status code to return for these errors
		DataIntegrityViolationException.class
	})
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ErrorRO resourceConflict(final HttpServletRequest request, final Exception ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Resource conflict"));
	}
	
	/**
	 * Returns an HTTP {@code 409 - CONFLICTED} status and an {@link ErrorRO} 
	 * describing what the conflict is (resource already exists).
	 * 
	 * @param request
	 * @param ex
	 * @return error description
	 */
	@ExceptionHandler({
		ResourceAlreadyExistsException.class
	})
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ErrorRO resourceConflict(final HttpServletRequest request, final ResourceAlreadyExistsException ex) {
		// TODO Create a REST-specific MessageId for this error
		return new ErrorRO(Objects.toString(ex, "Resource conflict"));
	}
	
}
