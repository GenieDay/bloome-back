package com.genieday.bloome.common;

import com.genieday.bloome.common.exception.AppException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    private int status;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result(HttpStatus.OK.value(), data);
    }

    public static <T> Result<T> error(AppException c, T data){
        return new Result(c.getErrorCode().getStatus().value(), data);
    }
}
