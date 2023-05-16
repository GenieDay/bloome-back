package com.genieday.bloome.user.controller;

import com.genieday.bloome.common.Result;
import com.genieday.bloome.user.dto.UserIdNameCheckRequest;
import com.genieday.bloome.user.dto.UserJoinRequest;
import com.genieday.bloome.user.dto.UserJoinResponse;
import com.genieday.bloome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/join/id-check")
    public ResponseEntity<Result<String>> checkIdName(@RequestBody UserIdNameCheckRequest userIdNameCheckRequest) {
        userService.idNameExist(userIdNameCheckRequest.getIdName());
        return ResponseEntity.ok().body(Result.success(userIdNameCheckRequest.getIdName()));
    }
}
