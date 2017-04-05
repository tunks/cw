/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.exception;

/**
 * Job runtime exception
 *
 * @author ebrimatunkara
 */
public class JobException extends RuntimeException {

    public JobException(String message) {
        super(message);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobException(Throwable cause) {
        super(cause);
    }

}
