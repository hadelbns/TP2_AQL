package org.example;

public class SoldeInsuffisantException extends Exception {
    public SoldeInsuffisantException() {
        super();
    }

    public SoldeInsuffisantException(String message) {
        super(message);
    }

    public SoldeInsuffisantException(String message, Throwable cause) {
        super(message, cause);
    }
}
