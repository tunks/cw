package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when token cannot be found in the request header
 *
 * @author Dileep K Mundakkapatta
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token missing in the request")
public class JwtTokenMissingException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String errorTitle;
    private String errorMessage;

    public JwtTokenMissingException(String errorTitle) {
        super(errorTitle);
        this.errorTitle = errorTitle;
    }

    public JwtTokenMissingException(String errorTitle, String errorMessage) {
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
