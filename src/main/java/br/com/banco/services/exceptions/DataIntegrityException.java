package br.com.banco.services.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

}
