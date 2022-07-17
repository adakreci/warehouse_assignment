package com.nl.warehouse.exceptions;

public class NoProductException extends RuntimeException {

    public NoProductException() {
        super();
    }

    public NoProductException(String msg) {
        super(msg);
    }

}
