package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="This user is already registered") 
public class  UserAlreadyExistingException extends Exception
{
	public UserAlreadyExistingException(String message)
	{
		super(message);
	}
	
}
