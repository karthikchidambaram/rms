package com.i2g.rms.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

/**
 * Implementation of JAX-RS {@link ExceptionMapper} to support handling of
 * {@link ResourceNotFoundException}s as an HTTP 404 response status.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Component
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
	
	@Override
	public Response toResponse(final ResourceNotFoundException e) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(String.valueOf(e))
				.build();
	}
	
}
