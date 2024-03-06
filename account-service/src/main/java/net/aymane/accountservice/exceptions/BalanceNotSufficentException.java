package net.aymane.accountservice.exceptions;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String message) {
        super(message);
    }
}
