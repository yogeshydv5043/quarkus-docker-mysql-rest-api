package org.ritpl.exception.errormodel;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }
}
