package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="invalid Token") 
public class JwtTokenMalformedException extends AuthenticationException 
{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public JwtTokenMalformedException(String errorMessage) {
        super(errorMessage);
        this.errorMessage=errorMessage;
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
