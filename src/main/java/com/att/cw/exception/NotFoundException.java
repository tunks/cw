/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ebrimatunkara
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(Long id) {
        super("Object not found " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
