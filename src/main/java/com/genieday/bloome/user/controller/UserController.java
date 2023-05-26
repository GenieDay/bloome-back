package com.genieday.bloome.user.controller;

import com.genieday.bloome.common.Result;
import com.genieday.bloome.user.dto.*;
import com.genieday.bloome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userJoinRequest
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<Result<UserJoinResponse>> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(Result.success(userJoinResponse));
    }

    /**
     * 아이디 중복 검사
     *
     * @param userIdNameCheckRequest
     * @return
     */
    @PostMapping("/join/id-check")
    public ResponseEntity<Result<UserIdNameCheckResponse>> checkIdName(@RequestBody UserIdNameCheckRequest userIdNameCheckRequest) {
        userService.idNameExist(userIdNameCheckRequest.getIdName());
        UserIdNameCheckResponse userIdNameCheckResponse = UserIdNameCheckResponse.builder()
                .idName(userIdNameCheckRequest.getIdName()).build();
        return ResponseEntity.ok().body(Result.success(userIdNameCheckResponse));
    }

    /**
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Result<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        return ResponseEntity.ok().body(Result.success(userLoginResponse));
    }
}