package com.genieday.bloome.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCode {
    // USER
    DUPLICATED_IDNAME(HttpStatus.CONFLICT, "해당 별명은 이미 사용중입니다.");

    private HttpStatus status;
    private String message;

}
