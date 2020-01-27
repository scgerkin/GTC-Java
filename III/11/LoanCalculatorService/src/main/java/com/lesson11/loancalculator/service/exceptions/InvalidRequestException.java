package com.lesson11.loancalculator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for handling invalid API requests.
 *
 * Any time Spring catches this exception, it will return a BAD REQUEST status
 * code (400) to the service consumer.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid values provided.")
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {}
    public InvalidRequestException(String msg) {super(msg);}
}
