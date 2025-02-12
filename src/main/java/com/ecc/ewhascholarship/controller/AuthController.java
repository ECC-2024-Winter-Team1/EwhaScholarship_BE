package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.*;
import com.ecc.ewhascholarship.security.JwtTokenProvider;
import com.ecc.ewhascholarship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody UserRegisterDto dto) {
        UserResponseDto response = userService.registerUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("사용자 등록 성공!", response)
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto dto) {
        System.out.println(dto.toString());
        LoginResponseDto response = userService.login(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("로그인 성공!", response));
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<ApiResponse> deleteUser(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        userService.deleteUser(userId);

        // 토큰 무효화 관련 로직 구현 필요

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("사용자 탈퇴 성공!", null));
    }

}
