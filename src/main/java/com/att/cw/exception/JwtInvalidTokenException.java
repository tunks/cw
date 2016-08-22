package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="invalid Token") 
public class JwtInvalidTokenException extends AuthenticationException 
{
	public JwtInvalidTokenException(String message)
	{
		super(message);
	}

}
