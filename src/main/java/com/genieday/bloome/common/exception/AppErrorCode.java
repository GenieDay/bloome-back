package com.genieday.bloome.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCode {
    // USER
    IDNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 잘못되었습니다."),
    DUPLICATED_IDNAME(HttpStatus.CONFLICT, "해당 별명은 이미 사용중입니다.");

    private HttpStatus status;
    private String message;

}
