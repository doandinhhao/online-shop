package com.yashmerino.online.shop.exceptions;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller advice that handles thrown exceptions in API requests.
 */
@RestControllerAdvice
@Validated
public class ApiExceptionHandler {

    /**
     * Handles the {@link ConstraintViolationException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();

        List<CustomFieldError> fieldErrors = new ArrayList<>();
        e.getConstraintViolations().forEach(error -> {
            CustomFieldError fieldError = new CustomFieldError();
            fieldError.setField(error.getPropertyPath().toString());
            fieldError.setMessage(error.getMessage());
            fieldErrors.add(fieldError);
        });

        fieldErrorResponse.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(fieldErrorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link MethodArgumentNotValidException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(MethodArgumentNotValidException e) {
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();

        List<CustomFieldError> fieldErrors = new ArrayList<>();
        e.getAllErrors().forEach(error -> {
            CustomFieldError fieldError = new CustomFieldError();
            fieldError.setField(((FieldError) error).getField());
            fieldError.setMessage(error.getDefaultMessage());
            fieldErrors.add(fieldError);
        });

        fieldErrorResponse.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(fieldErrorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link EmailAlreadyTakenException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {EmailAlreadyTakenException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<CustomErrorResponse> emailAlreadyTakenExceptionHandler(EmailAlreadyTakenException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    /**
     * Handles the {@link InvalidInputException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {InvalidInputException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> invalidInputExceptionHandler(InvalidInputException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link UserDoesntExistException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {UserDoesntExistException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> userDoesntExistExceptionHandler(UserDoesntExistException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@link UsernameAlreadyTakenException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {UsernameAlreadyTakenException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<CustomErrorResponse> usernameAlreadyTakenExceptionHandler(UsernameAlreadyTakenException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    /**
     * Handles the {@link EntityNotFoundException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@link BadCredentialsException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> badCredentialsExceptionHandler(BadCredentialsException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link CouldntUploadPhotoException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {CouldntUploadPhotoException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> couldntUploadPhotoExceptionHandler(CouldntUploadPhotoException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the {@link AccessDeniedException}
     *
     * @param e is the thrown exception.
     * @return <code>ResponseEntity</code>
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<CustomErrorResponse> accessDeniedExceptionHandler(AccessDeniedException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.FORBIDDEN.value());

        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }
}
