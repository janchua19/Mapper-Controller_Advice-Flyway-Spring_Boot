package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException extends RuntimeException {
    private String message;

    public CompanyNotFoundException (String message){
        this.message = message;
        //super(message);   //can also use this
    }

    @Override
    public String getMessage() {
        return message;
    }
}
