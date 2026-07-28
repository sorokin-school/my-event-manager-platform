package dev.sorokin.eventmanager.web;

import dev.sorokin.eventmanager.location.exceptions.LocationNameIsAlreadyExist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ServerErrorResponse> handleGenericException(
            Exception exception
    ) {
        log.error("Server error!", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerErrorResponse(
                        "Location name is already exists error",
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ValidationErrorResponse> validationErrorResponseResponseEntity(
            MethodArgumentNotValidException exception
    ) {
        List<ValidationErrorResponse.FieldError> fieldErrors = exception
                .getBindingResult()
                .getFieldErrors().stream()
                .map(
                    error -> new ValidationErrorResponse.FieldError(
                            error.getField(),
                            error.getDefaultMessage(),
                            error.getRejectedValue()
                    )
                ).toList();

        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " +
                        error.getDefaultMessage() + " " +
                        error.getRejectedValue()
                ).collect(Collectors.joining());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                message,
                fieldErrors
        );

        log.error("Got validation fields exception! {}", message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(
            LocationNameIsAlreadyExist.class
    )
    public ResponseEntity<ServerErrorResponse> locationNameExceptionHandler(
            LocationNameIsAlreadyExist exception
    ) {

        log.error("Got location name exception! {}", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ServerErrorResponse(
                        "Name is already exists error",
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }
}
