package com.articleinpictures.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Teemu Hirvonen
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidURLException extends RuntimeException {
    public InvalidURLException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public InvalidURLException(final String message) {
        super(message);
    }
}
