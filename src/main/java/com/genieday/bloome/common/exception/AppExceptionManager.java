package com.genieday.bloome.common.exception;

import com.genieday.bloome.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionManager {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> AppExceptionHandler(AppException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(Result.error(e,e.getMessage()));
    }

}
