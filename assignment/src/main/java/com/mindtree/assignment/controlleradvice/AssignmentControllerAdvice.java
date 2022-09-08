package com.mindtree.assignment.controlleradvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.assignment.exception.CartNotExistsException;
import com.mindtree.assignment.exception.NoCartDataFoundException;
import com.mindtree.assignment.exception.ProductNotFoundException;
import com.mindtree.assignment.exception.UserNotFoundException;
import com.mindtree.assignment.response.ApiError;

@ControllerAdvice
public class AssignmentControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> errorUserNotFoundException(
    		UserNotFoundException ex, WebRequest request) {
    	
    	ApiError apiError = new ApiError();
    	apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
    	apiError.setErrorMessage("User id not found in DB");

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> errorProductNotFoundException(
    		ProductNotFoundException ex, WebRequest request) {

    	ApiError apiError = new ApiError();
    	apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
    	apiError.setErrorMessage("No Product not found in DB");
       
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(CartNotExistsException.class)
    public ResponseEntity<Object> errorCartNotExistsException(
    		CartNotExistsException ex, WebRequest request) {

    	ApiError apiError = new ApiError();
    	apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
    	apiError.setErrorMessage("No Product exist in the cart");
      
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//            ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
            ApiError apiError = new ApiError();
            apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
        	apiError.setErrorMessage("No Product exist in the cart");
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoCartDataFoundException.class)
    public ResponseEntity<Object> errorNoCartDataFoundException(
    		ProductNotFoundException ex, WebRequest request) {
    	
    	ApiError apiError = new ApiError();
    	apiError.setErrorCode(HttpStatus.NOT_FOUND.value());
      	apiError.setErrorMessage("No Product not found in DB");

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

   
}