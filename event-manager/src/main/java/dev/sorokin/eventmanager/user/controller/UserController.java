package dev.sorokin.eventmanager.user.controller;

import dev.sorokin.eventmanager.user.dto.UserRegistrationRequest;
import dev.sorokin.eventmanager.user.dto.UserResponse;
import dev.sorokin.eventmanager.user.mapper.UserMapper;
import dev.sorokin.eventmanager.user.serivce.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registrationUser(
            @RequestBody UserRegistrationRequest registrationRequest
    ) {
        log.info("Got request for registration user: login={}", registrationRequest.login());


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        userMapper.toResponseFromDomain(
                                userService.createUser(
                                        userMapper.toDomainFromRegRequest(registrationRequest)
                                )
                        )
                );
    }


}
