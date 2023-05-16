package com.genieday.bloome.user.service;

import com.genieday.bloome.common.exception.AppErrorCode;
import com.genieday.bloome.common.exception.AppException;
import com.genieday.bloome.user.domain.User;
import com.genieday.bloome.user.domain.UserType;
import com.genieday.bloome.user.dto.UserJoinRequest;
import com.genieday.bloome.user.dto.UserJoinResponse;
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
    @Value("${jwt.token.secret}")
    private String secretKey;

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

}
