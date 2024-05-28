package com.ljs.userservice.controller;

import com.ljs.userservice.dto.UserDto;
import com.ljs.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveNickname(@Valid @RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }
}
