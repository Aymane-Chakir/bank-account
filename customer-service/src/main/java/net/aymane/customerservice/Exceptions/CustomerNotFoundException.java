package net.aymane.customerservice.Exceptions;

import org.apache.logging.log4j.message.Message;

public class CustomerNotFoundException extends Exception {
public CustomerNotFoundException(String message){
  super(message);
};
}
