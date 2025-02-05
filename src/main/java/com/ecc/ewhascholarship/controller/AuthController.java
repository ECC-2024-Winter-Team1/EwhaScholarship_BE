package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.config.JwtTokenProvider;
import com.ecc.ewhascholarship.dto.RegisterResponseDto;
import com.ecc.ewhascholarship.dto.UserDto;
import com.ecc.ewhascholarship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponseDto>> registerUser(@RequestBody UserDto dto) {
        UserDto registered = userService.registerUser(dto);
        String accessToken = jwtTokenProvider.createToken(registered.getId());
        RegisterResponseDto responseDto = new RegisterResponseDto(accessToken, registered);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("사용자 등록 성공!", responseDto)
                );
    }

}
