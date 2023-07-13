package com.suhIT.restroManager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());

        // Get all errors
        List<String> exceptionalErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, httpStatus);
    }



    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(
            NoSuchElementException noSuchElementException) {
        return new ResponseEntity<String>(noSuchElementException.getMessage(), noSuchElementException.getHttpStatus());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return new ResponseEntity<String>(userNotFoundException.getMessage(), userNotFoundException.getHttpStatus());
    }

    @ExceptionHandler(value = UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExists(
            UserAlreadyExists userAlreadyExists) {
        return new ResponseEntity<String>(userAlreadyExists.getMessage(), userAlreadyExists.getHttpStatus());
    }

    @ExceptionHandler(value = SalaryNotFoundException.class)
    public ResponseEntity<String> handleSalaryNotFoundException(
            SalaryNotFoundException salaryNotFoundException) {
        return new ResponseEntity<String>(salaryNotFoundException.getMessage(), salaryNotFoundException.getHttpStatus());
    }

    @ExceptionHandler(value = ActiveSalaryHasAnEndDateError.class)
    public ResponseEntity<String> handleActiveSalaryHasAnEndDateError(
            ActiveSalaryHasAnEndDateError activeSalaryHasAnEndDateError) {
        return new ResponseEntity<String>(activeSalaryHasAnEndDateError.getMessage(), activeSalaryHasAnEndDateError.getHttpStatus());
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFoundException(
            ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<String>(itemNotFoundException.getMessage(), itemNotFoundException.getHttpStatus());
    }





}
