package com.burnsm.stockmarket;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
