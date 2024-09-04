package org.demo.users.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MainExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MainExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        ex.getBindingResult().getAllErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .map(msg -> StringUtils.isBlank(msg) ? "Default message undefined" : msg)
                                .sorted(Comparator.comparing(msg->msg.split(" ")[0]))
                                .map(msg -> new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(), msg))
                .collect(Collectors.toList())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {

        log.debug("An unexpected error ocurred: " + exception.getMessage());
        log.debug("exception: "+exception);
        return ResponseEntity.internalServerError().body(new ErrorResponse(
                List.of(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),"An unexpected error ocurred: " + exception.getMessage()))));
    }
}
