package com.suhIT.restroManager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveSalaryHasAnEndDateError extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private String message;
}
