package com.att.cw.dto;

/*
 * This class is returned with any error response by a controller in the response body
 * author : Dileep K Mundakkapatta
 * 
 */
public class ErrorResponse {

    private int errorCode;
    private String errorTitle;
    private String errorMessage;

    public ErrorResponse() {

    }

    public ErrorResponse(int errorCode, String errorTitle, String errorMessage) {
        this.errorCode = errorCode;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
