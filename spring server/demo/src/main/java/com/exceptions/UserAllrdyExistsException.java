package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.RESET_CONTENT, reason = "Duplicate in DB")
public class UserAllrdyExistsException extends RuntimeException
{
    public UserAllrdyExistsException(String message)
    {
        super(message);
    }
}
