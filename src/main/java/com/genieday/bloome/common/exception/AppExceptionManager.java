package com.genieday.bloome.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genieday.bloome.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class AppExceptionManager {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> AppExceptionHandler(AppException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(Result.error(e, e.getMessage()));
    }

    public static void setErrorResponse(HttpServletResponse response, AppErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        response.getWriter().write(objectMapper.writeValueAsString(
                new ResponseEntity(Result.error(
                        AppException.builder()
                                .errorCode(errorCode)
                                .message(errorCode.getMessage())
                                .build(), errorCode.getMessage()
                ), errorCode.getStatus())
        ));

    }
}
