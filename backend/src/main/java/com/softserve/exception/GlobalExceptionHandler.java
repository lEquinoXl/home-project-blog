package com.softserve.exception;

import com.softserve.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error nullEntityReferenceExceptionHandler(HttpServletRequest request, NullEntityReferenceException exception) {
        Error e = new Error();
        e.setCode("400");
        e.setMessage(exception.getMessage());
        return e;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Error accessForbiddenExceptionHandler(HttpServletRequest request, AccessDeniedException exception) {
        Error e = new Error();
        e.setCode("403");
        e.setMessage("Access denied");
        return e;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error entityNotFoundExceptionHandler(HttpServletRequest request, EntityNotFoundException exception) {
        Error e = new Error();
        e.setCode("404");
        e.setMessage("Entity not found");
        return e;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Error internalServerErrorHandler(HttpServletRequest request, Exception exception) {
        Error e = new Error();
        e.setCode("500");
        e.setMessage("Server error");
        return e;
    }


}
