/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.exception;

/**
 * JobApplication run time exception
 * @author ebrimatunkara
 */
public class JobApplicationException extends RuntimeException{

    public JobApplicationException() {
    }

    public JobApplicationException(String message) {
        super(message);
    }

    public JobApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobApplicationException(Throwable cause) {
        super(cause);
    }
    
}
