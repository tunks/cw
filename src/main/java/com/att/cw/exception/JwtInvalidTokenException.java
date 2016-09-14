package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="invalid Token") 
public class JwtInvalidTokenException extends AuthenticationException 
{
	/**
	 * Thrown when token is invalid
	 * @author Dileep K Mundakkapatta
	 */

	private static final long serialVersionUID = -1097763310088316896L;
	
	private String errorTitle;
	private String errorMessage;

	public JwtInvalidTokenException(String errorTitle)
	{
		super(errorTitle);
		this.errorTitle=errorTitle;
	}
	public JwtInvalidTokenException(String errorTitle,String errorMessage)
	{
		super(errorTitle);
		this.errorTitle=errorTitle;
		this.errorMessage=errorMessage;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
