package com.genieday.bloome.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AppException extends RuntimeException{
    private AppErrorCode errorCode;
    private String message;
}
