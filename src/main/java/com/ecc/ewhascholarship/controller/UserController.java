package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.UserResponseDto;
import com.ecc.ewhascholarship.dto.UserUpdateDto;
import com.ecc.ewhascholarship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        UserResponseDto response = userService.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("사용자 정보 조회 성공!",response));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(Principal principal, @RequestBody UserUpdateDto userUpdateDto) {
        UUID userId = UUID.fromString(principal.getName());
        UserResponseDto response = userService.updateUser(userId, userUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("사용자 정보 수정 성공!",response));
    }
}
