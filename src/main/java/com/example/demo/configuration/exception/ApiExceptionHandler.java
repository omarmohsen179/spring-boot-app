package com.example.demo.configuration.exception;

import com.example.demo.configuration.exception.Types.NoDataException;
import com.example.demo.configuration.exception.Types.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ApiException> handleApiRequestException(RequestException e){

        HttpStatus badRequest  =  HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        ),badRequest);
    }
    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ApiException> handleNoDataException(NoDataException e){
        HttpStatus error  =  HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ApiException(
                e.getMessage(),
                error,
                ZonedDateTime.now(ZoneId.of("Z"))
        ),error);
    }
    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<ApiException> handleConflict(
            RuntimeException ex, WebRequest request) {
        HttpStatus error  =  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>( new ApiException(
                "custom internal error",
                error,
                ZonedDateTime.now(ZoneId.of("Z"))
        ),error);
    }
}
