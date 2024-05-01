package com.project.shopapp.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RessourceNotFoundException
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RessourceNotFoundException(String message) {
        super(message);
    }

}