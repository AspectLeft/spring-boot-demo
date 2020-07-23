package io.aspect.springbootdemo.handler;

import io.aspect.springbootdemo.exception.InvalidRequestException;
import io.aspect.springbootdemo.exception.NotFoundException;
import io.aspect.springbootdemo.res.ErrorResource;
import io.aspect.springbootdemo.res.FieldResource;
import io.aspect.springbootdemo.res.InvalidErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleNotFound(final RuntimeException e) {
        return new ResponseEntity<>(ErrorResource.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(final InvalidRequestException e) {
        return new ResponseEntity<>(InvalidErrorResource.builder().message(e.getMessage())
                .errors(e.getErrors().getFieldErrors().stream().map(fieldError -> FieldResource.builder()
                        .resource(fieldError.getObjectName())
                        .code(fieldError.getCode())
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build()).collect(Collectors.toList()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
