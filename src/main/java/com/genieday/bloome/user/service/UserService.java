package com.genieday.bloome.user.service;

import com.genieday.bloome.common.exception.AppErrorCode;
import com.genieday.bloome.common.exception.AppException;
import com.genieday.bloome.security.JwtTokenUtil;
import com.genieday.bloome.security.RedisAccessTokenUtil;
import com.genieday.bloome.user.User;
import com.genieday.bloome.user.UserType;
import com.genieday.bloome.user.dto.UserJoinRequest;
import com.genieday.bloome.user.dto.UserJoinResponse;
import com.genieday.bloome.user.dto.UserLoginRequest;
import com.genieday.bloome.user.dto.UserLoginResponse;
import com.genieday.bloome.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserJpaRepository userJpaRepository;
    private final RedisAccessTokenUtil redisAccessTokenUtil;
    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60; // 1h

    public UserJoinResponse join(UserJoinRequest dto) {
        idNameExist(dto.getIdName());
        User savedUser = userJpaRepository.save(
                User.builder()
                        .name(dto.getName())
                        .idName(dto.getIdName())
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))
                        .userType(UserType.ROLE_USER)
                        .build()
        );

        return UserJoinResponse.builder()
                .name(savedUser.getName())
                .idName(savedUser.getIdName())
                .build();
    }

    public void idNameExist(String idName) {
        Boolean idNameExist = false;
        Optional<User> user = userJpaRepository.findByIdName(idName);
        if (user.isPresent()) {
            idNameExist = true;
        }
        if(idNameExist){
            throw new AppException(AppErrorCode.DUPLICATED_IDNAME,
                    AppErrorCode.DUPLICATED_IDNAME.getMessage());
        }
    }

    public UserLoginResponse login(UserLoginRequest dto) {
        User user = userJpaRepository.findByIdName(dto.getIdName())
                .orElseThrow(() -> {
                    throw new AppException(AppErrorCode.IDNAME_NOT_FOUND,
                            AppErrorCode.IDNAME_NOT_FOUND.getMessage());
                });

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AppException(AppErrorCode.INVALID_PASSWORD, AppErrorCode.INVALID_PASSWORD.getMessage());
        }

        String generatedJwt = JwtTokenUtil.createToken(user.getIdName(), secretKey, expireTimeMs);

        if (redisAccessTokenUtil.hasAccessToken(user.getIdName())) {
            redisAccessTokenUtil.saveBlockAccessToken(
                    redisAccessTokenUtil.getAccessToken(dto.getIdName())
            );
        }
        redisAccessTokenUtil.saveAccessToken(user.getIdName(), generatedJwt);
        return UserLoginResponse.builder()
                .idName(user.getIdName())
                .accessToken(generatedJwt)
                .build();
    }
}
