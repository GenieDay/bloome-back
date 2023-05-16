package com.genieday.bloome.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException{
    private AppErrorCode errorCode;
    private String message;
}
