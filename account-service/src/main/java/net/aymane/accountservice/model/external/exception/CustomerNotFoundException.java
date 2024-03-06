package net.aymane.accountservice.model.external.exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException ( String message){
        super(message);
    }
}
