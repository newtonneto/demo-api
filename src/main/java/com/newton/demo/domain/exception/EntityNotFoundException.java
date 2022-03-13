package com.newton.demo.domain.exception;

public class EntityNotFoundException extends RulesException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
