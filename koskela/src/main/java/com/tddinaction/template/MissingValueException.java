package com.tddinaction.template;

/**
 * @author Lasse Koskela
 */
public class MissingValueException extends RuntimeException {

    public MissingValueException() {
    }

    public MissingValueException(String message) {
        super(message);
    }

}
