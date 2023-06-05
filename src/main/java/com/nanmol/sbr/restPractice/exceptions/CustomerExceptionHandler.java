package com.nanmol.sbr.restPractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.StubNotFoundException;

@ControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<CustomerErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        CustomerErrorResponse cer = new CustomerErrorResponse();
        cer.setStatus(HttpStatus.NOT_FOUND.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<CustomerErrorResponse>(cer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<CustomerErrorResponse> handleInvalidCustomer(HttpMessageNotReadableException ex){
        CustomerErrorResponse cer = new CustomerErrorResponse();
        cer.setStatus(HttpStatus.BAD_REQUEST.value());
        if(ex.getMessage().contains("JSON parse error")){
            cer.setMsg("INVALID CUSTOMER DATA: " + ex.getMessage() );
        }else {
            cer.setMsg(ex.getMessage());
        }
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<CustomerErrorResponse>(cer, HttpStatus.BAD_REQUEST);
    }


}
