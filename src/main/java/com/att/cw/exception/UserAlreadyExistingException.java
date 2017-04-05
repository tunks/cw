package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the newly registered user is already existing with the same email
 * id
 *
 * @author Dileep K Mundakkapatta
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user is already registered")
public class UserAlreadyExistingException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String errorTitle;
    private String errorMessage;

    public UserAlreadyExistingException(String errorTitle) {
        super(errorTitle);
        this.errorTitle = errorTitle;
    }

    public UserAlreadyExistingException(String errorTitle, String errorMessage) {
        super(errorTitle);
        this.errorTitle = errorTitle;
        this.errorMessage = errorMessage;
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
